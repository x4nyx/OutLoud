package fpmibsu.outloud.dao.mysql;

import fpmibsu.outloud.dao.*;
import fpmibsu.outloud.entitiy.Group;
import fpmibsu.outloud.entitiy.User;
import fpmibsu.outloud.enumfiles.Type;
import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.entitiy.Post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostDaoImpl extends BaseDaoImpl implements PostDao {


    private Post makePost(ResultSet resultSet) throws SQLException, PersistentException {
        Post post = new Post();
        post.setId(resultSet.getInt("id"));
        TransactionFactoryImpl temp = new TransactionFactoryImpl();
        Transaction tempTrans = temp.createTransaction();
        UserDao userDao = tempTrans.createDao(UserDao.class);
        post.setCreator(userDao.findUserById(resultSet.getInt("creatorid")));
        post.setText(resultSet.getString("text"));
        post.setTitle(resultSet.getString("title"));
        post.setViewCount(resultSet.getInt("viewCount"));
        TransactionFactoryImpl tempGroup = new TransactionFactoryImpl();
        Transaction tempTransGroup = temp.createTransaction();
        GroupDao groupDao = tempTransGroup.createDao(GroupDao.class);
        post.setGroup(groupDao.findGroupById(resultSet.getInt("groupid")));
        return post;
    }

    public List<Post> findAllPosts() throws PersistentException {
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
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return posts;
    }

    public Post findPostById(Integer id) throws PersistentException {
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
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return post;
    }

    public boolean deletePostById(Integer id) throws PersistentException {
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
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean isExist(Integer id) throws PersistentException {
        return findPostById(id) != null;
    }

    public boolean createPost(Post entity) throws PersistentException {
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
                resultSet.close();
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public Post updatePost(Post entity) throws PersistentException {
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
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return post;
    }
}
