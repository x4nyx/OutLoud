package fpmibsu.outloud.service;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.dao.GroupDao;
import fpmibsu.outloud.entitiy.Group;

import java.util.List;

public class GroupService {
    public List<Group> getAllGroups() throws DaoException {
        GroupDao groupDao = new GroupDao();
        groupDao.createConnection();
        return groupDao.findAllGroups();
    }

    public Group getGroupById(Integer groupId) throws DaoException {
        GroupDao groupDao = new GroupDao();
        groupDao.createConnection();
        return groupDao.findGroupById(groupId);
    }
}
