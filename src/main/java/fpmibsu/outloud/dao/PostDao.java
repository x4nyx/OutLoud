package fpmibsu.outloud.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.*;


public class PostDao {
    private static Post makePost(ResultSet resultSet) throws SQLException, DaoException {
        Post post = new Post();
        post.setId(resultSet.getInt("id"));
        post.setCreator(UserDao.findUserById(resultSet.getInt("creatorid")));
        post.setText(resultSet.getString("text"));
        post.setTitle(resultSet.getString("title"));
        post.setViewCount(resultSet.getInt("viewCount"));
        post.setGroup(GroupDao.findGroupById(resultSet.getInt("groupid")));
        return post;
    }

    public static List<Post> findAllPosts() throws DaoException {
        List<Post> posts = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT * FROM posts;");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Post post = makePost(resultSet);
                posts.add(post);
            }
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return posts;
    }

    public static Post findPostById(Integer id) throws DaoException {
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
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return post;
    }

    public static boolean deletePostById(Integer id) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "DELETE FROM posts WHERE id=" + id + ";";
            statement.executeUpdate(sqlString);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean isExist(Integer id) throws DaoException {
        return findPostById(id) != null;
    }

    public static boolean createPost(Post entity) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "INSERT INTO posts(groupid, creatorid, viewCount, text, title) VALUES";
            sqlString += entity + ";";
            statement.executeUpdate(sqlString);
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID();");
            if(resultSet.next()) {
                entity.setId(resultSet.getInt("LAST_INSERT_ID()"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    
    public static Post updatePost(Post entity) throws DaoException {
        Post post;
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            post = findPostById(entity.getId());
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE posts SET ");
            sqlStringBuilder.append("groupid='").append(entity.getGroup().getId()).append("', ");
            sqlStringBuilder.append("creatorid='").append(entity.getCreator().getId()).append("', ");
            sqlStringBuilder.append("text='").append(entity.getText()).append("', ");
            sqlStringBuilder.append("title='").append(entity.getTitle()).append("', ");
            sqlStringBuilder.append("viewCount='").append(entity.getViewCount()).append("' ");
            sqlStringBuilder.append("WHERE id=").append(entity.getId()).append(";");
            String sqlString = new String(sqlStringBuilder);           
            statement.executeUpdate(sqlString);
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return post;
    }
}
