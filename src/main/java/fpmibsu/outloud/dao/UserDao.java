package fpmibsu.outloud.dao;

import fpmibsu.outloud.entitiy.User;
import fpmibsu.outloud.exception.PersistentException;

import java.util.List;

public interface UserDao extends BaseDao<User>{
    boolean isExist(Integer userid) throws PersistentException;
    List<User> findAllUsers() throws PersistentException;
    User findUserById(Integer id) throws PersistentException;
    User findUserByLogin(String login) throws PersistentException;
    boolean deleteUserById(Integer id) throws PersistentException;
    boolean createUser(User entity) throws PersistentException;
    User updateUser(User entity) throws PersistentException;
    User validate(String login, String password) throws PersistentException;

}
