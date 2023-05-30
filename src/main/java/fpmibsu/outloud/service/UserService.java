package fpmibsu.outloud.service;

import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.entitiy.User;

public interface UserService extends Service{

    boolean isExist(String login) throws PersistentException;

    User validate(String login, String password) throws PersistentException;

    boolean createUser(String login, String password) throws PersistentException;

    User getUser(String login) throws PersistentException;

    User getUserById(Integer id) throws PersistentException;
}
