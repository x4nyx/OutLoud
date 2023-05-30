package fpmibsu.outloud.service.impl;

import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.dao.GroupDao;
import fpmibsu.outloud.entitiy.Group;
import fpmibsu.outloud.service.GroupService;

import java.util.List;

public class GroupServiceImpl extends ServiceImpl implements GroupService {
    public List<Group> getAllGroups() throws PersistentException {
        GroupDao groupDao = transaction.createDao(GroupDao.class);
        return groupDao.findAllGroups();
    }

    public Group getGroupById(Integer groupId) throws PersistentException {
        GroupDao groupDao = transaction.createDao(GroupDao.class);
        return groupDao.findGroupById(groupId);
    }
}
