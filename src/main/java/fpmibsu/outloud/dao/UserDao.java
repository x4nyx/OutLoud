package fpmibsu.outloud.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.*;
import fpmibsu.outloud.enumfiles.Type;


public class UserDao {
    public static boolean isExist(Integer userid) throws DaoException {
        int count = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT COUNT(*) AS count FROM users WHERE id="
                                                                        + userid + ";");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                count = resultSet.getInt("count");
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
        return (count > 0);
    }

    private static User makeUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setConfimation(resultSet.getInt("confirmation") == 1);
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(Type.valueOf(resultSet.getString("role")));
        return user;
    }

    public static List<User> findAllUsers() throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT * FROM users;");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                User user = makeUser(resultSet);
                users.add(user);
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
        return users;
    }

    public static User findUserById(Integer id) throws DaoException {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT * FROM users WHERE id=" + id + ";");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                user = makeUser(resultSet);
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
        return user;
    }

    public static boolean deleteUserById(Integer id) throws DaoException {
        if(!isExist(id)) {
            return false;
        }
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "DELETE FROM users WHERE id=" + id + ";";
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

    public static boolean createUser(User entity) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "INSERT INTO users(name, login, password, role, confirmation) VALUES";
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

    public static User updateUser(User entity) throws DaoException {
        User user;
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
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
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
