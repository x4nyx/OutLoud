package fpmibsu.outloud.dao;

import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.Album;
import fpmibsu.outloud.entitiy.Track;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AlbumDao {
    public static List<Track> findAllTracksInAlbum(Integer albumid) throws DaoException {
        List<Track> tracks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String SQL_GROUPMEMBERS = "SELECT * FROM tracklist WHERE tracklist.albumid=";
        try {
            connection = ConnectionCreator.createConnection();
            String sqString = SQL_GROUPMEMBERS + albumid + ";";
            statement = connection.prepareStatement(sqString);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                tracks.add(TrackDao.findEntityById(resultSet.getInt("trackid")));
            }
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(resultSet);
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException ignored) {}
        }
        return tracks;
    }

    public static boolean addTrack(Integer albumid, Integer trackid) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "INSERT INTO tracklist(albumid, trackid) VALUES ";
            sqlString += "('" + albumid + "', '" + trackid + "');";
            statement.executeUpdate(sqlString);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch (SQLException ignored) {}
        }
        return true;
    }

    public static boolean deleteTrackFromAlbum(Integer albumid, Integer trackid) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "DELETE FROM tracklist WHERE albumid=" + albumid + " AND trackid=" +
                    trackid + ";";
            statement.executeUpdate(sqlString);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch (SQLException ignored) {}
        }
        return true;
    }

    public static boolean isAlbumHasTracks(Integer albumid) throws DaoException {
        int count = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT COUNT(*) AS count FROM tracklist WHERE albumid=" + albumid + ";");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(resultSet);
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch (SQLException ignored) {
            }
        }
        return count > 0;
    }

    public static List<Track> getTracks(Integer albumid) throws DaoException {
        if(!isAlbumHasTracks(albumid)) {
            return null;
        }
        List<Track> tracks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT * FROM tracklist WHERE albumid=" +albumid + ";");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer id = resultSet.getInt("trackid");
                tracks.add(TrackDao.findEntityById(id));
            }
        } catch(SQLException | DaoException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(resultSet);
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException ignored) {}
        }
        return tracks;
    }


    public static Album makeAlbum(ResultSet resultSet) throws SQLException, DaoException {
        Album album = new Album();
        album.setId(resultSet.getInt("id"));
        album.setName(resultSet.getString("name"));
        album.setCreator(UserDao.findEntityById(resultSet.getInt("creatorid")));
        album.setCreationDate(resultSet.getDate("creationDate"));
        album.setIsPlaylist(resultSet.getInt("isPlaylist") == 1);
        album.setTrackList(getTracks(album.getId()));
        return album;
    }

    public static boolean isExist(Integer albumid) throws DaoException{
        return findEntityById(albumid) != null;
    }
    public static List<Album> findAll() throws DaoException {
        List<Album> albums = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT * FROM albums;");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                albums.add(makeAlbum(resultSet));
            }
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(resultSet);
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException ignored) {}
        }
        return albums;
    }

    public static Album findEntityById(Integer id) throws DaoException {
        Album album = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT * FROM albums WHERE id=" + id + ";");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                album = makeAlbum(resultSet);
            }
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(resultSet);
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException ignored) {}
        }
        return album;
    }

    public static List<Album> findAlbumByName(String nameSubstr) throws DaoException {
        List<Album> albums = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            String sqlString = "SELECT * FROM albums WHERE LOWER(name) LIKE '%"
                    + nameSubstr + "%';";
            statement = connection.prepareStatement(sqlString);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                albums.add(makeAlbum(resultSet));
            }
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(resultSet);
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException ignored) {}
        }
        return albums;
    }

    public static boolean delete(Integer id) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "DELETE FROM albums WHERE id=" + id + ";";
            statement.executeUpdate(sqlString);
            sqlString = "DELETE FROM tracklist WHERE albumid =" + id + ";";
            statement.executeUpdate(sqlString);
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException ignored) {}
        }
        return true;
    }

    public static Album update(Album album) throws DaoException {
        if(!isExist(album.getId())) {
            return null;
        }
        Album albumToUpdate = null;
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            albumToUpdate = findEntityById(album.getId());
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE albums SET ");
            sqlStringBuilder.append("name='").append(album.getName()).append("', ");
            sqlStringBuilder.append("creatorid='").append(album.getCreator().getId()).append("', ");
            sqlStringBuilder.append("isPlaylist='").append(album.getIntIsPlayList()).append("', ");
            sqlStringBuilder.append("creationDate='").append(album.getCreationDate()).append("' ");
            sqlStringBuilder.append("WHERE id=").append(album.getId()).append(";");
            String sqlString = new String(sqlStringBuilder);
            statement.executeUpdate(sqlString);
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException ignored) {}
        }
        return albumToUpdate;
    }
    public static boolean deleteAllTracks(Integer albumid) throws DaoException{
        if(!isAlbumHasTracks(albumid)) {
            return false;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT * FROM tracklist WHERE albumid=" +albumid + ";");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer id = resultSet.getInt("trackid");
                deleteTrackFromAlbum(albumid, id);
            }
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(resultSet);
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException ignored) {}
        }
        return true;
    }

    public static boolean updateTrackList(Integer albumid, List<Track> tracks) throws DaoException{
        if(tracks == null || tracks.isEmpty()) {
            return false;
        }
        deleteAllTracks(albumid);
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            for(Track track : tracks) {
                String sqlString = "INSERT INTO tracklist(albumid, trackid) VALUES";
                sqlString += "('" + albumid + "', '" + track.getId() + "');";
                statement.executeUpdate(sqlString);
            }
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException ignored) {}
        }
        return true;
    }

    public static boolean create(Album album) throws DaoException {
        if(isExist(album.getId())) {
            return false;
        }
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "INSERT INTO albums(id, name, isPlaylist, creatorid, creationDate) VALUES ";
            sqlString += album.toString() + ";";
            statement.executeUpdate(sqlString);
            updateTrackList(album.getId(), album.getTrackList());
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException ignored) {}
        }
        return true;
    }
}
