package fpmibsu.outloud.dao.mysql;

import fpmibsu.outloud.dao.GenreDao;
import fpmibsu.outloud.dao.Transaction;
import fpmibsu.outloud.dao.UserDao;
import fpmibsu.outloud.entitiy.Genre;
import fpmibsu.outloud.entitiy.User;
import fpmibsu.outloud.enumfiles.Type;
import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.dao.TrackDao;
import fpmibsu.outloud.entitiy.Track;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrackDaoImpl extends BaseDaoImpl implements TrackDao {

    public Track makeTrack(ResultSet resultSet) throws SQLException, PersistentException {
        Track track = new Track();
        track.setId(resultSet.getInt("id"));
        TransactionFactoryImpl temp = new TransactionFactoryImpl();
        Transaction tempTrans = temp.createTransaction();
        UserDao userDao = tempTrans.createDao(UserDao.class);
        track.setCreator(userDao.findUserById(resultSet.getInt("creatorid")));
        track.setDate(resultSet.getDate("date"));
        TransactionFactoryImpl tempGenre = new TransactionFactoryImpl();
        Transaction tempTransGenre = temp.createTransaction();
        GenreDao genreDao = tempTrans.createDao(GenreDao.class);
        track.setGenre(genreDao.findGenreById(resultSet.getInt("genreid")));
        track.setName(resultSet.getString("name"));
        track.setPlaysCount(resultSet.getInt("playsCount"));
        return track;
    }

    public List<Track> findAllTracks() throws PersistentException {
        List<Track> tracks = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = this.connection.prepareStatement("SELECT * FROM tracks;");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Track track = makeTrack(resultSet);
                tracks.add(track);
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


    public Track findTrackById(Integer id) throws PersistentException {
        Track track = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.prepareStatement("SELECT * FROM tracks WHERE id='" + id + "';");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                track = makeTrack(resultSet);
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
        return track;
    }


    public List<Track> findTracksByName(String nameSubstr) throws PersistentException {
        List<Track> tracks = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sqlString = "SELECT * FROM tracks WHERE LOWER(name) LIKE '%"
                    + nameSubstr + "%';";
            statement = this.connection.prepareStatement(sqlString);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                tracks.add(makeTrack(resultSet));
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

    public boolean isExists(Integer id) throws PersistentException {
        return findTrackById(id) != null;
    }

    public boolean createTrack(Track track) throws PersistentException {
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.createStatement();
            String sqlString = "INSERT INTO tracks(creatorid, date, genreid, name, playsCount) VALUES";
            sqlString += track + ";";
            statement.executeUpdate(sqlString);
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID();");
            if(resultSet.next()) {
                track.setId(resultSet.getInt("LAST_INSERT_ID()"));
            }
            //else {
            //    logger.error("There is no autoincremented index after trying to add record into table `tracks`");
            // }
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


    public Track updateTrack(Track track) throws PersistentException {
        Track trackToUpdate;
        Statement statement = null;
        try{
            statement = this.connection.createStatement();
            trackToUpdate = findTrackById(track.getId());
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE tracks SET ");
            sqlStringBuilder.append("creatorid='").append(track.getCreator().getId()).append("', ");
            sqlStringBuilder.append("date='").append(track.getDate()).append("', ");
            sqlStringBuilder.append("genreid='").append(track.getGenre().getId()).append("', ");
            sqlStringBuilder.append("name='").append(track.getName()).append("', ");
            sqlStringBuilder.append("playsCount='").append(track.getPlaysCount()).append("'");
            sqlStringBuilder.append(" WHERE id=").append(track.getId()).append(";");
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
        return trackToUpdate;
    }

    public boolean deleteTrackById(Integer id) throws PersistentException {
        if(!isExists(id)) {
            return false;
        }
        Statement statement = null;
        try{
            statement = this.connection.createStatement();
            String sqlString = "DELETE FROM tracks WHERE id='" + id + "';";
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
}
