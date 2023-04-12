package fpmibsu.outloud.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.*;
import fpmibsu.outloud.enumfiles.Type;

//s
public class UserDao {
    private static final String SQL_SELECT_ALL_USERS = 
                                    "SELECT * FROM users;";
    private static final String SQL_SELECT_ALL_ID =
                                    "SELECT id FROM users;";
    
    public static List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_USERS);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setConfimation(resultSet.getInt("confirmation") == 1);
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Type.valueOf(resultSet.getString("role")));
                users.add(user);
            }
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch(SQLException exception) {}
        }
        return users;
    }

    public static User findEntityById(Integer id) throws DaoException {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_ID);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                if(resultSet.getInt("id") == id) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(Type.valueOf(resultSet.getString("role")));
                    user.setConfimation(resultSet.getBoolean("confirmation"));
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
        return user;
    }

    public static boolean delete(Integer id) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "DELETE FROM users WHERE id=" + id + ";";
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

    public static boolean create(User entity) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "INSERT INTO users(id, name, login, password, role, confirmation) VALUES";
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
    
    public static User update(User entity) throws DaoException {
        User user = null;
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            user = findEntityById(entity.getId());
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE users SET ");
            sqlStringBuilder.append("name=").append(entity.getName()).append(", ");
            sqlStringBuilder.append("login=").append(entity.getLogin()).append(", ");
            sqlStringBuilder.append("password=").append(entity.getPassword()).append(", ");
            sqlStringBuilder.append("role=").append(entity.getRole()).append(", ");
            sqlStringBuilder.append("confirmation=").append(entity.getConfirmation());
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
        return user;
    }
}
