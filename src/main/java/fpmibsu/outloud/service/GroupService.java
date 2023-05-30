package fpmibsu.outloud.service;

import fpmibsu.outloud.entitiy.Group;
import fpmibsu.outloud.exception.PersistentException;
import java.util.List;

public interface GroupService extends Service{

    List<Group> getAllGroups() throws PersistentException;

    Group getGroupById(Integer groupId) throws PersistentException;
}
