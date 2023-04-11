package fpmibsu.outloud.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.*;
import fpmibsu.outloud.enumfiles.Type;

//TO-DO
public class GroupDao {
    private static final String SQL_SELECT_ALL_USERS = 
                                    "SELECT * FROM users;";
    private static final String SQL_SELECT_ALL_ID =
                                    "SELECT id FROM users;";
    
    //TO-DO
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
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Type.valueOf(resultSet.getString("role")));
                user.setConfimation(resultSet.getBoolean("confirmation"));
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
        return users;
    }

    //TO-DO
    public static Group findEntityById(Integer id) throws DaoException {
        Group group = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_ID);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                if(resultSet.getInt("id") == id) {
                    group = new Group();
                    group.setId(resultSet.getInt("id"));
                    group.setName(resultSet.getString("name"));
                    // TO-DO
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
        return group;
    }

    //TO-DO
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

    //TO-DO
    public static boolean create(User entity) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "INSER INTO users(id, name, login, password, role, isConfirmed) VALUES";
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
    public static User update(User entity) throws DaoException {
        User user = null;
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            //user = findEntityById(entity.getId());
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
