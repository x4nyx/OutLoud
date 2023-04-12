package fpmibsu.outloud.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.*;


public class GroupDao {
    private static final String SQL_SELECT_ALL_GROUPS = 
                                    "SELECT * FROM groups;";
    private static final String SQL_SELECT_ALL_ID =
                                    "SELECT id FROM groups;";
    
    public static List<User> findMembers(Group group) throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String SQL_GROUPMEMBERS = "SELECT * FROM groupmembers WHERE groupmembers.groupid=";
        try {
            connection = ConnectionCreator.createConnection();
            String sqString = SQL_GROUPMEMBERS + group.getId() + ";";
            statement = connection.prepareStatement(sqString);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                User user = new User();
                user = UserDao.findEntityById(resultSet.getInt("userid"));
                users.add(user);
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

    private static Group groupCreate(ResultSet resultSet) throws DaoException {
        Group group = new Group();
        try {
            group.setId(resultSet.getInt("id"));
            group.setName(resultSet.getString("name"));
            group.setCreator(UserDao.findEntityById(resultSet.getInt("creatorid")));
            group.setUserNum(resultSet.getInt("userNum"));
            group.setIsConfirmed(resultSet.getInt("confirmation") == 1);
            group.setDescription(resultSet.getString("description"));
            group.setMembers(findMembers(group));
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return group;
    }

    public static List<Group> findAll() throws DaoException {
        List<Group> groups = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_GROUPS);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                groups.add(groupCreate(resultSet));
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
        return groups;
    }

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
                    group = groupCreate(resultSet);
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

    public static boolean delete(Integer id) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "DELETE FROM groups WHERE id=" + id + ";";
            statement.executeUpdate(sqlString);
            sqlString = "DELETE FROM groupmembers WHERE groupid =" + id + ";";
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

    public static boolean updateGroupMemebers(Integer groupid, List<User> members) throws DaoException{
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            for(User member : members) {
                String sqlString = "INSERT INTO groupmembers(groupid, userid) VALUES";
                sqlString += "(" + groupid + ", " + member.getId() + ");";
                statement.executeUpdate(sqlString);
            }
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

    public static boolean create(Group entity) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "INSER INTO groups(id, creatorid, userNum, confirmation, description, name) VALUES";
            sqlString += entity.toString() + ";";            
            statement.executeUpdate(sqlString);
            updateGroupMemebers(entity.getId(), entity.getMembers());
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
    
    public static Group update(Group entity) throws DaoException {
        Group group = null;
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            group = findEntityById(entity.getId());
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE groups SET ");
            sqlStringBuilder.append("name=").append(entity.getName()).append(", ");
            sqlStringBuilder.append("creatorid=").append(entity.getCreator().getId()).append(", ");
            sqlStringBuilder.append("description=").append(entity.getDescription()).append(", ");
            sqlStringBuilder.append("userNum=").append(entity.getUserNum()).append(", ");
            sqlStringBuilder.append("confirmation=").append(entity.getIsConfirmed());
            sqlStringBuilder.append("WHERE id=").append(group.getId()).append(";");
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
        return group;
    }
}
