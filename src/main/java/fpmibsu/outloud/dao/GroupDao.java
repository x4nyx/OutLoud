package fpmibsu.outloud.dao;

import fpmibsu.outloud.entitiy.Group;
import fpmibsu.outloud.entitiy.User;
import fpmibsu.outloud.exception.PersistentException;

import java.util.List;

public interface GroupDao extends BaseDao<Group> {
    List<User> findMembersInGroup(Integer groupid) throws PersistentException;
    List<Group> findAllGroups() throws PersistentException;
    Group findGroupById(Integer id) throws PersistentException;
    boolean isExist(Integer id) throws PersistentException;
    boolean isGroupHasMembers(Integer groupid) throws PersistentException;
    boolean deleteAllMembersFromGroup(Integer groupid) throws PersistentException;
    boolean updateGroupMembers(Integer groupid, List<User> members) throws PersistentException;
    boolean deleteMemberFromGroup(Integer userid, Integer groupid) throws PersistentException;
    boolean addMemberToGroup(Integer userid, Integer groupid) throws PersistentException;
    boolean createGroup(Group entity) throws PersistentException;
    Group updateGroup(Group entity) throws PersistentException;
}
