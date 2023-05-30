package fpmibsu.outloud.service.impl;

import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.dao.UserDao;
import fpmibsu.outloud.entitiy.User;
import fpmibsu.outloud.enumfiles.Type;
import fpmibsu.outloud.service.UserService;

public class UserServiceImpl extends ServiceImpl implements UserService {
    public boolean isExist(String login) throws PersistentException {
        UserDao userDao = transaction.createDao(UserDao.class);
        return userDao.findUserByLogin(login) != null;
    }

    public User validate(String login, String password) throws PersistentException {
        UserDao userDao = transaction.createDao(UserDao.class);
        return userDao.validate(login, password);
    }

    public boolean createUser(String login, String password) throws PersistentException {
        UserDao userDao = transaction.createDao(UserDao.class);
        if(userDao.findUserByLogin(login) != null) {
            return false;
        }
        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setRole(Type.USER);
        newUser.setConfimation(false);
        return userDao.createUser(newUser);
    }

    public User getUser(String login) throws PersistentException {
        UserDao userDao = transaction.createDao(UserDao.class);
        return userDao.findUserByLogin(login);
    }

    public User getUserById(Integer id) throws PersistentException {
        UserDao userDao = transaction.createDao(UserDao.class);
        return userDao.findUserById(id);
    }
}
