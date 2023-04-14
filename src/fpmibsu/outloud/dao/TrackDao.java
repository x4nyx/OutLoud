package fpmibsu.outloud.dao;

import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.Track;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TrackDao {


    public static Track makeTrack(ResultSet resultSet) throws SQLException, DaoException {
        Track track = new Track();
        track.setId(resultSet.getInt("id"));
        track.setCreator(UserDao.findEntityById(resultSet.getInt("creatorid")));
        track.setDate(resultSet.getDate("date"));
        track.setGenre(GenreDao.findEntityById(resultSet.getInt("genreid")));
        track.setName(resultSet.getString("name"));
        track.setPlaysCount(resultSet.getInt("playsCount"));
        return track;
    }


    public static boolean isExists(Integer id) throws DaoException {
        return findEntityById(id) != null;
    }


    public static List<Track> findAll() throws DaoException{
        List<Track> tracks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT * FROM tracks;");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Track track = makeTrack(resultSet);
                tracks.add(track);
            }
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException ignored) {}
        }
        return tracks;
    }


    public static Track findEntityById(Integer id) throws DaoException{
        Track track = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT * FROM tracks WHERE id=" + id + ";");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                track = makeTrack(resultSet);
            }
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException ignored) {}
        }
        return track;
    }


    public static List<Track> findTracksByName(String nameSubstr) throws DaoException{
        List<Track> tracks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            String sqlString = "SELECT * FROM tracks WHERE LOWER(name) LIKE '%"
                                                            + nameSubstr + "%';";
            statement = connection.prepareStatement(sqlString);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                tracks.add(makeTrack(resultSet));
            }
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException ignored) {}
        }
        return tracks;
    }


    public static boolean create(Track track) throws DaoException{
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "INSERT INTO tracks(id, creatorid, date, genreid, name, playsCount) VALUES";
            sqlString += track.toString() + ";";
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


    public static Track update(Track track) throws DaoException{
        Track trackToUpdate;
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            trackToUpdate = findEntityById(track.getId());
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE users SET ");
            sqlStringBuilder.append("creatorid='").append(track.getCreator().getId()).append("', ");
            sqlStringBuilder.append("date='").append(track.getDate()).append("', ");
            sqlStringBuilder.append("genreid='").append(track.getGenre().getId()).append("', ");
            sqlStringBuilder.append("name='").append(track.getName()).append("', ");
            sqlStringBuilder.append("playsCount='").append(track.getPlaysCount()).append("'");
            sqlStringBuilder.append(" WHERE id=").append(track.getId()).append(";");
            String sqlString = new String(sqlStringBuilder);
            System.out.println(sqlString);
            statement.executeUpdate(sqlString);
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException ignored) {}
        }
        return trackToUpdate;
    }

    //TO-DO
    public static boolean delete(Integer id) throws DaoException{
        if(!isExists(id)) {
            return false;
        }
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "DELETE FROM tracks WHERE id=" + id + ";";
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
}
