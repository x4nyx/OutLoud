package fpmibsu.outloud.service;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.dao.GroupDao;
import fpmibsu.outloud.entitiy.Group;

import java.util.List;

public class GroupService {
    GroupDao groupDao = new GroupDao();

    public GroupService() {
        groupDao.createConnection();
    }

    public List<Group> getAllGroups() throws DaoException {
        return groupDao.findAllGroups();
    }

    public Group getGroupById(Integer groupId) throws DaoException {
        return groupDao.findGroupById(groupId);
    }
}
