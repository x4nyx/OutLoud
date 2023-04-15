package fpmibsu.outloud.dao;

import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.Track;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TrackDao extends AbstractDao{
    public TrackDao() {super();}
    public TrackDao(Connection connection) {
        super(connection);
    }

    public Track makeTrack(ResultSet resultSet) throws SQLException, DaoException {
        Track track = new Track();
        UserDao userDao = new UserDao(ConnectionCreator.createConnection());
        GenreDao genreDao = new GenreDao(ConnectionCreator.createConnection());
        track.setId(resultSet.getInt("id"));
        track.setCreator(userDao.findUserById(resultSet.getInt("creatorid")));
        track.setDate(resultSet.getDate("date"));
        track.setGenre(genreDao.findGenreById(resultSet.getInt("genreid")));
        track.setName(resultSet.getString("name"));
        track.setPlaysCount(resultSet.getInt("playsCount"));
        ConnectionCreator.close(userDao);
        ConnectionCreator.close(genreDao);
        return track;
    }


    public List<Track> findAllTracks() throws DaoException{
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
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return tracks;
    }


    public Track findTrackById(Integer id) throws DaoException{
        Track track = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.prepareStatement("SELECT * FROM tracks WHERE id=" + id + ";");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                track = makeTrack(resultSet);
            }
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return track;
    }


    public List<Track> findTracksByName(String nameSubstr) throws DaoException{
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
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return tracks;
    }

    public boolean isExists(Integer id) throws DaoException {
        return findTrackById(id) != null;
    }

    public boolean createTrack(Track track) throws DaoException{
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
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    public Track updateTrack(Track track) throws DaoException{
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
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return trackToUpdate;
    }

    public boolean deleteTrackById(Integer id) throws DaoException{
        if(!isExists(id)) {
            return false;
        }
        Statement statement = null;
        try{
            statement = this.connection.createStatement();
            String sqlString = "DELETE FROM tracks WHERE id=" + id + ";";
            statement.executeUpdate(sqlString);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
