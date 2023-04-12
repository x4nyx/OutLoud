package fpmibsu.outloud.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.*;
import fpmibsu.outloud.enumfiles.Type;

//TO-DO
public class ReportDao {
    private static final String SQL_SELECT_ALL_USERS =
            "SELECT * FROM users;";

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
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch(SQLException exception) {}
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
                User user = makeUser(resultSet);
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
            statement = connection.prepareStatement("SELECT * FROM users WHERE id=" + id + ";");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                user = makeUser(resultSet);
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
        User user;
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            user = findEntityById(entity.getId());
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE users SET ");
            sqlStringBuilder.append("name='").append(entity.getName()).append("', ");
            sqlStringBuilder.append("login='").append(entity.getLogin()).append("', ");
            sqlStringBuilder.append("password='").append(entity.getPassword()).append("', ");
            sqlStringBuilder.append("role='").append(entity.getRole()).append("', ");
            sqlStringBuilder.append("confirmation='").append(entity.getIntConfirmation()).append("'");
            sqlStringBuilder.append(" WHERE id=").append(entity.getId()).append(";");
            String sqlString = new String(sqlStringBuilder);
            System.out.println(sqlString);
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
