package fpmibsu.outloud.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.*;

//TO-DO
public class PostDao {
    private static final String SQL_SELECT_ALL_USERS = 
                                    "SELECT * FROM posts;";
    private static final String SQL_SELECT_ALL_ID =
                                    "SELECT id FROM posts;";
    
    public List<Post> findAll() throws DaoException {
        List<Post> posts = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_USERS);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setCreator(UserDao.findEntityById(resultSet.getInt("creatorid")));
                post.setText(resultSet.getString("text"));
                post.setTitle(resultSet.getString("title"));
                post.setViewCount(resultSet.getInt("viewsCount"));
                post.setGroup(GroupDao.findEntityById(resultSet.getInt("groupid")));
            }
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                resultSet.close();
                connection.close();
                statement.close();
            } catch(SQLException exception) {}
        }
        return posts;
    }

    //TO-DO
    public Genre findEntityById(Integer id) throws DaoException {
        Genre genre = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_ID);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                if(resultSet.getInt("id") == id) {
                    genre = new Genre();
                    genre.setId(resultSet.getInt("id"));
                    genre.setName(resultSet.getString("name"));
                    break;
                }
            }
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                resultSet.close();
                connection.close();
                statement.close();
            } catch(SQLException exception) {}
        }
        return genre;
    }

    //TO-DO
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
                connection.close();
                statement.close();
            } catch(SQLException exception) {}
        }
        return true;
    }

    //TO-DO
    public boolean create(Genre entity) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "INSER INTO genres(id, name) VALUES";
            sqlString += entity.toString() + ";";            
            statement.executeUpdate(sqlString);
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                connection.close();
                statement.close();
            } catch(SQLException exception) {}
        }
        return true;
    }
    
    //TO-DO
    public Genre update(Genre entity) throws DaoException {
        Genre genre = null;
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            genre = findEntityById(entity.getId());
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE genres SET ");
            sqlStringBuilder.append("name=").append(entity.getName()).append(", ");
            sqlStringBuilder.append("WHERE id=").append(entity.getId()).append(";");
            String sqlString = new String(sqlStringBuilder);           
            statement.executeUpdate(sqlString);
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                connection.close();
                statement.close();
            } catch(SQLException exception) {}
        }
        return genre;
    }
}
