package fpmibsu.outloud.dao.mysql;

import fpmibsu.outloud.dao.AlbumDao;
import fpmibsu.outloud.dao.Transaction;
import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.dao.TrackDao;
import fpmibsu.outloud.dao.UserDao;
import fpmibsu.outloud.entitiy.Album;
import fpmibsu.outloud.entitiy.Track;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlbumDaoImpl extends BaseDaoImpl implements AlbumDao {

    @Override
    public List<Track> findAllTracksInAlbum(Integer albumid) throws PersistentException {
        List<Track> tracks = new ArrayList<>();
        TrackDao trackDao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String SQL_GROUPMEMBERS = "SELECT * FROM tracklist WHERE tracklist.albumid=";
        try {
            String sqString = SQL_GROUPMEMBERS + albumid + ";";
            statement = this.connection.prepareStatement(sqString);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                tracks.add(trackDao.findTrackById(resultSet.getInt("trackid")));
            }
        } catch(SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return tracks;
    }

    @Override
    public boolean addTrackToAlbum(Integer albumid, Integer trackid) {
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            String sqlString = "INSERT INTO tracklist(albumid, trackid) VALUES ";
            sqlString += "('" + albumid + "', '" + trackid + "');";
            statement.executeUpdate(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean deleteTrackFromAlbum(Integer albumid, Integer trackid) {
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            String sqlString = "DELETE FROM tracklist WHERE albumid=" + albumid + " AND trackid=" +
                    trackid + ";";
            statement.executeUpdate(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean isAlbumTrackListEmpty(Integer albumid) throws PersistentException {
        int count = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = this.connection.prepareStatement("SELECT COUNT(*) AS count FROM tracklist WHERE albumid=" + albumid + ";");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return count <= 0;
    }

    @Override
    public List<Track> getTracksFromAlbum(Integer albumid) throws PersistentException {
        if(isAlbumTrackListEmpty(albumid)) {
            return null;
        }
        List<Track> tracks = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.prepareStatement("SELECT * FROM tracklist WHERE albumid=" +albumid + ";");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer id = resultSet.getInt("trackid");
                TransactionFactoryImpl temp = new TransactionFactoryImpl();
                Transaction tempTrans = temp.createTransaction();
                TrackDao trackDao = tempTrans.createDao(TrackDao.class);
                tracks.add(trackDao.findTrackById(id));
            }
        } catch(SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch(SQLException | NullPointerException ignored) {}
        }
        return tracks;
    }

    private Album makeAlbum(ResultSet resultSet) throws SQLException, PersistentException {
        TransactionFactoryImpl temp = new TransactionFactoryImpl();
        Transaction tempTrans = temp.createTransaction();
        UserDao userDao = tempTrans.createDao(UserDao.class);
        Album album = new Album();
        album.setId(resultSet.getInt("id"));
        album.setName(resultSet.getString("name"));
        album.setCreator(userDao.findUserById(resultSet.getInt("creatorid")));
        album.setCreationDate(resultSet.getDate("creationDate"));
        album.setIsPlaylist(resultSet.getInt("isPlaylist") == 1);
        album.setTrackList(getTracksFromAlbum(album.getId()));
        resultSet.close();
        return album;
    }
    @Override
    public boolean isExist(Integer albumid) throws PersistentException {
        return findAlbumById(albumid) != null;
    }
    @Override
    public List<Album> findAllAlbums() throws PersistentException {
        List<Album> albums = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = this.connection.prepareStatement("SELECT * FROM albums;");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                albums.add(makeAlbum(resultSet));
            }
        } catch(SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return albums;
    }
    @Override
    public Album findAlbumById(Integer id) throws PersistentException {
        Album album = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.prepareStatement("SELECT * FROM albums WHERE id=" + id + ";");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                album = makeAlbum(resultSet);
            }
        } catch(SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return album;
    }
    @Override
    public List<Album> findAlbumByName(String nameSubstr) throws PersistentException {
        List<Album> albums = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sqlString = "SELECT * FROM albums WHERE LOWER(name) LIKE '%"
                    + nameSubstr + "%';";
            statement = this.connection.prepareStatement(sqlString);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                albums.add(makeAlbum(resultSet));
            }
        } catch(SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return albums;
    }
    @Override
    public boolean deleteAlbumById(Integer id) throws PersistentException {
        Statement statement = null;
        try{
            statement = connection.createStatement();
            String sqlString = "DELETE FROM albums WHERE id=" + id + ";";
            statement.executeUpdate(sqlString);
            sqlString = "DELETE FROM tracklist WHERE albumid =" + id + ";";
            statement.executeUpdate(sqlString);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    @Override
    public Album updateAlbum(Album album) throws PersistentException {
        if(!isExist(album.getId())) {
            return null;
        }
        Album albumToUpdate;
        Statement statement = null;
        try{
            statement = this.connection.createStatement();
            albumToUpdate = findAlbumById(album.getId());
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE albums SET ");
            sqlStringBuilder.append("name='").append(album.getName()).append("', ");
            sqlStringBuilder.append("creatorid='").append(album.getCreator().getId()).append("', ");
            sqlStringBuilder.append("isPlaylist='").append(album.getIntIsPlayList()).append("', ");
            sqlStringBuilder.append("creationDate='").append(album.getCreationDate()).append("' ");
            sqlStringBuilder.append("WHERE id=").append(album.getId()).append(";");
            String sqlString = new String(sqlStringBuilder);
            statement.executeUpdate(sqlString);
        } catch(SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return albumToUpdate;
    }
    @Override
    public boolean deleteAllTracksFromAlbum(Integer albumid) throws PersistentException {
        if(isAlbumTrackListEmpty(albumid)) {
            return false;
        }
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.prepareStatement("SELECT * FROM tracklist WHERE albumid=" +albumid + ";");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer id = resultSet.getInt("trackid");
                deleteTrackFromAlbum(albumid, id);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    @Override
    public boolean updateTrackList(Integer albumid, List<Track> tracks) throws PersistentException {
        if(tracks == null || tracks.isEmpty()) {
            return false;
        }
        deleteAllTracksFromAlbum(albumid);
        Statement statement = null;
        try{
            statement = this.connection.createStatement();
            for(Track track : tracks) {
                String sqlString = "INSERT INTO tracklist(albumid, trackid) VALUES";
                sqlString += "('" + albumid + "', '" + track.getId() + "');";
                statement.executeUpdate(sqlString);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    @Override
    public boolean createAlbum(Album album) throws PersistentException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = this.connection.createStatement();
            String sqlString = "INSERT INTO albums(name, isPlaylist, creatorid, creationDate) VALUES ";
            sqlString += album + ";";
            statement.executeUpdate(sqlString);
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID();");
            if(resultSet.next()) {
                album.setId(resultSet.getInt("LAST_INSERT_ID()"));
            }
            updateTrackList(album.getId(), album.getTrackList());
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
