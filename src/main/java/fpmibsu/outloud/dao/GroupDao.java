package fpmibsu.outloud.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.*;


public class GroupDao extends AbstractDao{
    public GroupDao() {super();}
    public GroupDao(Connection connection) {
        super(connection);
    }

    public List<User> findMembersInGroup(Integer groupid) throws DaoException {
        UserDao userDao = new UserDao();
        List<User> users = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String SQL_GROUPMEMBERS = "SELECT * FROM groupmembers WHERE groupmembers.groupid=";
        try {
            String sqString = SQL_GROUPMEMBERS + groupid + ";";
            statement = this.connection.prepareStatement(sqString);
            resultSet = statement.executeQuery();
            userDao.createConnection();
            while(resultSet.next()) {
                User user = userDao.findUserById(resultSet.getInt("userid"));
                users.add(user);
            }
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(userDao);
                ConnectionCreator.close(resultSet);
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    private Group makeGroup(ResultSet resultSet) throws DaoException {
        Group group = new Group();
        UserDao userDao = new UserDao();
        try {
            userDao.createConnection();
            group.setId(resultSet.getInt("id"));
            group.setName(resultSet.getString("name"));
            group.setCreator(userDao.findUserById(resultSet.getInt("creatorid")));
            group.setUserNum(resultSet.getInt("userNum"));
            group.setIsConfirmed(resultSet.getInt("confirmation") == 1);
            group.setDescription(resultSet.getString("description"));
            group.setMembers(findMembersInGroup(group.getId()));
            ConnectionCreator.close(userDao);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return group;
    }

    public List<Group> findAllGroups() throws DaoException {
        List<Group> groups = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = this.connection.prepareStatement("SELECT * FROM groupst;");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                groups.add(makeGroup(resultSet));
            }
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(resultSet);
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return groups;
    }

    public Group findGroupById(Integer id) throws DaoException {
        Group group = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.prepareStatement("SELECT * FROM groupst WHERE id=" + id + ";");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                group = makeGroup(resultSet);
            }
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(resultSet);
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return group;
    }

    public boolean isExist(Integer id) throws DaoException {
        return findGroupById(id) != null;
    }

    public boolean deleteGroupById(Integer id) throws DaoException {
        Statement statement = null;
        try{
            statement = this.connection.createStatement();
            String sqlString = "DELETE FROM groupst WHERE id=" + id + ";";
            statement.executeUpdate(sqlString);
            sqlString = "DELETE FROM groupmembers WHERE groupid =" + id + ";";
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

    public boolean isGroupHasMembers(Integer groupid) throws DaoException {
        int count = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.prepareStatement("SELECT COUNT(*) AS count FROM groupmembers WHERE groupid="
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
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return count > 0;
    }

    public boolean deleteAllMembersFromGroup(Integer groupid) throws DaoException{
        Statement statement = null;
        try{
            statement = this.connection.createStatement();
            String sqlString = "DELETE FROM groupmembers WHERE groupid=" + groupid + ";";
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
        return true;
    }

    public boolean updateGroupMembers(Integer groupid, List<User> members) throws DaoException{
        if(isGroupHasMembers(groupid)) {
            deleteAllMembersFromGroup(groupid);
        }
        Statement statement = null;
        try{
            statement = this.connection.createStatement();
            for(User member : members) {
                String sqlString = "INSERT INTO groupmembers(groupid, userid) VALUES";
                sqlString += "('" + groupid + "', '" + member.getId() + "');";
                statement.executeUpdate(sqlString);
            }
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean deleteMemberFromGroup(Integer userid, Integer groupid) throws DaoException {
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            String sqlString = "DELETE FROM groupmembers WHERE userid=" + userid + " AND groupid=" +
                                                                                        groupid + ";";
            statement.executeUpdate(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionCreator.close(statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean addMemberToGroup(Integer userid, Integer groupid) throws DaoException {
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            String sqlString = "INSERT INTO groupmembers(groupid, userid) VALUES ";
            sqlString += "('" + groupid + "', '" + userid + "');";
            statement.executeUpdate(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionCreator.close(statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean createGroup(Group entity) throws DaoException {
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.createStatement();
            String sqlString = "INSERT INTO groupst(creatorid, userNum, confirmation, description, name) VALUES ";
            sqlString += entity + ";";
            statement.executeUpdate(sqlString);
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID();");
            if(resultSet.next()) {
                entity.setId(resultSet.getInt("LAST_INSERT_ID()"));
            }
            updateGroupMembers(entity.getId(), entity.getMembers());
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
    
    public Group updateGroup(Group entity) throws DaoException {
        Group group;
        Statement statement = null;
        try{
            statement = this.connection.createStatement();
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
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return group;
    }
}
