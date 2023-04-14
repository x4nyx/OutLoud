package fpmibsu.outloud.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.*;


public class PostDao {
    private static final String SQL_SELECT_ALL_USERS = 
                                    "SELECT * FROM posts;";



    private static Post makePost(ResultSet resultSet) throws SQLException, DaoException {
        Post post = new Post();
        post.setId(resultSet.getInt("id"));
        post.setCreator(UserDao.findEntityById(resultSet.getInt("creatorid")));
        post.setText(resultSet.getString("text"));
        post.setTitle(resultSet.getString("title"));
        post.setViewCount(resultSet.getInt("viewCount"));
        post.setGroup(GroupDao.findEntityById(resultSet.getInt("groupid")));
        return post;
    }

    public static List<Post> findAll() throws DaoException {
        List<Post> posts = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_USERS);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Post post = makePost(resultSet);
                posts.add(post);
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
        return posts;
    }

    public static Post findEntityById(Integer id) throws DaoException {
        Post post = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT * FROM posts WHERE id=" + id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                post = makePost(resultSet);
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
        return post;
    }

    public static boolean delete(Integer id) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "DELETE FROM posts WHERE id=" + id + ";";
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

    public static boolean isExist(Integer id) throws DaoException {
        return findEntityById(id) != null;
    }

    public static boolean create(Post entity) throws DaoException {
        if(isExist(entity.getId())) {
            return false;
        }
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "INSERT INTO posts(id, groupid, creatorid, viewCount, text, title) VALUES";
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
    
    public static Post update(Post entity) throws DaoException {
        Post post = null;
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            post = findEntityById(entity.getId());
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE posts SET ");
            sqlStringBuilder.append("groupid='").append(entity.getGroup().getId()).append("', ");
            sqlStringBuilder.append("creatorid='").append(entity.getCreator().getId()).append("', ");
            sqlStringBuilder.append("text='").append(entity.getText()).append("', ");
            sqlStringBuilder.append("title='").append(entity.getTitle()).append("', ");
            sqlStringBuilder.append("viewCount='").append(entity.getViewCount()).append("' ");
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
        return post;
    }
}
