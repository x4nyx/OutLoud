package fpmibsu.outloud.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.*;


public class PostDao extends AbstractDao{
    public PostDao() {super();}
    public PostDao(Connection connection) {
        super(connection);
    }

    private Post makePost(ResultSet resultSet) throws SQLException, DaoException {
        Post post = new Post();
        UserDao userDao = new UserDao(ConnectionCreator.createConnection());
        GroupDao groupDao = new GroupDao(ConnectionCreator.createConnection());
        post.setId(resultSet.getInt("id"));
        post.setCreator(userDao.findUserById(resultSet.getInt("creatorid")));
        post.setText(resultSet.getString("text"));
        post.setTitle(resultSet.getString("title"));
        post.setViewCount(resultSet.getInt("viewCount"));
        post.setGroup(groupDao.findGroupById(resultSet.getInt("groupid")));
        ConnectionCreator.close(userDao);
        ConnectionCreator.close(groupDao);
        return post;
    }

    public List<Post> findAllPosts() throws DaoException {
        List<Post> posts = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = this.connection.prepareStatement("SELECT * FROM posts;");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Post post = makePost(resultSet);
                posts.add(post);
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
        return posts;
    }

    public Post findPostById(Integer id) throws DaoException {
        Post post = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.prepareStatement("SELECT * FROM posts WHERE id=" + id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                post = makePost(resultSet);
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
        return post;
    }

    public boolean deletePostById(Integer id) throws DaoException {
        Statement statement = null;
        try{
            statement = this.connection.createStatement();
            String sqlString = "DELETE FROM posts WHERE id=" + id + ";";
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

    public boolean isExist(Integer id) throws DaoException {
        return findPostById(id) != null;
    }

    public boolean createPost(Post entity) throws DaoException {
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.createStatement();
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
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    
    public Post updatePost(Post entity) throws DaoException {
        Post post;
        Statement statement = null;
        try{
            statement = this.connection.createStatement();
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
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return post;
    }
}
