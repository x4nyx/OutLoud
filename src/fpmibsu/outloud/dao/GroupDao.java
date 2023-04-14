package fpmibsu.outloud.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.*;


public class GroupDao {
    public static List<User> findMembersInGroup(Integer groupid) throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String SQL_GROUPMEMBERS = "SELECT * FROM groupmembers WHERE groupmembers.groupid=";
        try {
            connection = ConnectionCreator.createConnection();
            String sqString = SQL_GROUPMEMBERS + groupid + ";";
            statement = connection.prepareStatement(sqString);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                User user = UserDao.findUserById(resultSet.getInt("userid"));
                users.add(user);
            }
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(resultSet);
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    private static Group makeGroup(ResultSet resultSet) throws DaoException {
        Group group = new Group();
        try {
            group.setId(resultSet.getInt("id"));
            group.setName(resultSet.getString("name"));
            group.setCreator(UserDao.findUserById(resultSet.getInt("creatorid")));
            group.setUserNum(resultSet.getInt("userNum"));
            group.setIsConfirmed(resultSet.getInt("confirmation") == 1);
            group.setDescription(resultSet.getString("description"));
            group.setMembers(findMembersInGroup(group.getId()));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return group;
    }

    public static List<Group> findAllGroups() throws DaoException {
        List<Group> groups = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT * FROM groupst;");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                groups.add(makeGroup(resultSet));
            }
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(resultSet);
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return groups;
    }

    public static Group findGroupById(Integer id) throws DaoException {
        Group group = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT * FROM groupst WHERE id=" + id + ";");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                group = makeGroup(resultSet);
            }
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(resultSet);
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return group;
    }

    public static boolean isExist(Integer id) throws DaoException {
        return findGroupById(id) != null;
    }

    public static boolean deleteGroupById(Integer id) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "DELETE FROM groupst WHERE id=" + id + ";";
            statement.executeUpdate(sqlString);
            sqlString = "DELETE FROM groupmembers WHERE groupid =" + id + ";";
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

    public static boolean isGroupHasMembers(Integer groupid) throws DaoException {
        int count = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT COUNT(*) AS count FROM groupmembers WHERE groupid="
                                                                                        + groupid + ";");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionCreator.close(resultSet);
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return count > 0;
    }

    public static boolean deleteAllMembersFromGroup(Integer groupid) throws DaoException{
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "DELETE FROM groupmembers WHERE groupid=" + groupid + ";";
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
        return true;
    }

    public static boolean updateGroupMembers(Integer groupid, List<User> members) throws DaoException{
        if(isGroupHasMembers(groupid)) {
            deleteAllMembersFromGroup(groupid);
        }
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            for(User member : members) {
                String sqlString = "INSERT INTO groupmembers(groupid, userid) VALUES";
                sqlString += "('" + groupid + "', '" + member.getId() + "');";
                statement.executeUpdate(sqlString);
            }
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
        return true;
    }

    public static boolean deleteMemberFromGroup(Integer userid, Integer groupid) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "DELETE FROM groupmembers WHERE userid=" + userid + " AND groupid=" +
                                                                                        groupid + ";";
            statement.executeUpdate(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean addMemberToGroup(Integer userid, Integer groupid) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "INSERT INTO groupmembers(groupid, userid) VALUES ";
            sqlString += "('" + groupid + "', '" + userid + "');";
            statement.executeUpdate(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean createGroup(Group entity) throws DaoException {
        if(isExist(entity.getId())) {
            return false;
        }
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "INSERT INTO groupst(id, creatorid, userNum, confirmation, description, name) VALUES ";
            sqlString += entity + ";";
            statement.executeUpdate(sqlString);
            updateGroupMembers(entity.getId(), entity.getMembers());
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
    
    public static Group updateGroup(Group entity) throws DaoException {
        Group group;
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            group = findGroupById(entity.getId());
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE groupst SET ");
            sqlStringBuilder.append("name='").append(entity.getName()).append("', ");
            sqlStringBuilder.append("creatorid='").append(entity.getCreator().getId()).append("', ");
            sqlStringBuilder.append("description='").append(entity.getDescription()).append("', ");
            sqlStringBuilder.append("userNum='").append(entity.getUserNum()).append("', ");
            sqlStringBuilder.append("confirmation='").append(entity.getIntConfirmation()).append("' ");
            sqlStringBuilder.append("WHERE id=").append(group.getId()).append(";");
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
        return group;
    }
}
