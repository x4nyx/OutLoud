package fpmibsu.outloud.dao.mysql;

import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.dao.UserDao;
import fpmibsu.outloud.entitiy.User;
import fpmibsu.outloud.enumfiles.Type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    public boolean isExist(Integer userid) throws PersistentException {
        int count = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = this.connection.prepareStatement("SELECT COUNT(*) AS count FROM users WHERE id='"
                    + userid + "';");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                count = resultSet.getInt("count");
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
        return (count > 0);
    }

    private User makeUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setConfimation(resultSet.getInt("confirmation") == 1);
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(Type.valueOf(resultSet.getString("role")));
        return user;
    }

    public List<User> findAllUsers() throws PersistentException {
        List<User> users = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = this.connection.prepareStatement("SELECT * FROM users;");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                User user = makeUser(resultSet);
                users.add(user);
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
        return users;
    }

    public User findUserById(Integer id) throws PersistentException {
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.prepareStatement("SELECT * FROM users WHERE id='" + id + "';");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                user = makeUser(resultSet);
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
        return user;
    }

    public User findUserByLogin(String login) throws PersistentException {
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.prepareStatement("SELECT * FROM users WHERE login='" + login + "';");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                user = makeUser(resultSet);
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
        return user;
    }

    public boolean deleteUserById(Integer id) throws PersistentException {
        if(!isExist(id)) {
            return false;
        }
        Statement statement = null;
        try{
            statement = this.connection.createStatement();
            String sqlString = "DELETE FROM users WHERE id='" + id + "';";
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

    public boolean createUser(User entity) throws PersistentException {
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.createStatement();
            String sqlString = "INSERT INTO users(name, login, password, role, confirmation) VALUES";
            sqlString += entity + ";";
            statement.executeUpdate(sqlString);
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID();");
            if(resultSet.next()) {
                entity.setId(resultSet.getInt("LAST_INSERT_ID()"));
            }
            //  else {
            //      logger.error("There is no autoincremented index after trying to add record into table `users`");
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

    public User updateUser(User entity) throws PersistentException {
        User user;
        Statement statement = null;
        try{
            statement = this.connection.createStatement();
            user = findUserById(entity.getId());
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE users SET ");
            sqlStringBuilder.append("name='").append(entity.getName()).append("', ");
            sqlStringBuilder.append("login='").append(entity.getLogin()).append("', ");
            sqlStringBuilder.append("password='").append(entity.getPassword()).append("', ");
            sqlStringBuilder.append("role='").append(entity.getRole()).append("', ");
            sqlStringBuilder.append("confirmation='").append(entity.getIntConfirmation()).append("'");
            sqlStringBuilder.append(" WHERE id=").append(entity.getId()).append(";");
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
        return user;
    }

    public User validate(String login, String password) throws PersistentException {
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.prepareStatement("SELECT * FROM users WHERE login='" + login +
                    "' AND password='" + password + "';");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                user = makeUser(resultSet);
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
        return user;
    }
}
