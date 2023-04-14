package fpmibsu.outloud.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.*;


public class GenreDao {
    private static final String SQL_SELECT_ALL_USERS = 
                                    "SELECT * FROM genres;";
    private static final String SQL_SELECT_ALL_ID =
                                    "SELECT id FROM genres;";

    private static Genre makeGenre(ResultSet resultSet) throws DaoException {
        Genre genre = null;
        try {
            genre = new Genre();
            genre.setId(resultSet.getInt("id"));
            genre.setName(resultSet.getString("name"));
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return genre;
    }

    public static List<Genre> findAll() throws DaoException {
        List<Genre> genres = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_USERS);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                genres.add(makeGenre(resultSet));
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
        return genres;
    }

    public static Genre findEntityById(Integer id) throws DaoException {
        Genre genre = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionCreator.createConnection();
            String sqlString = "SELECT * FROM genres WHERE id=" + id + ";";
            statement = connection.prepareStatement(sqlString);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                genre = makeGenre(resultSet);
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
        return genre;
    }

    public boolean delete(Integer id) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "DELETE FROM genres WHERE id=" + id + ";";
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

    public boolean create(Genre entity) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "INSERT INTO genres(id, name) VALUES";
            sqlString += entity.toString() + ";";            
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
    
    public Genre update(Genre entity) throws DaoException {
        Genre genre = null;
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            genre = findEntityById(entity.getId());
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE genres SET ");
            sqlStringBuilder.append("name='").append(entity.getName()).append("' ");
            sqlStringBuilder.append("WHERE id=").append(entity.getId()).append(";");
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
        return genre;
    }
}
