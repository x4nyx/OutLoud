package fpmibsu.outloud.service;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.dao.UserDao;
import fpmibsu.outloud.entitiy.User;
import fpmibsu.outloud.enumfiles.Type;

public class UserService {
    public boolean isExist(String login) throws DaoException {
        UserDao userDao = new UserDao();
        userDao.createConnection();
        return userDao.findUserByLogin(login) != null;
    }

    public boolean validate(String login, String password) throws DaoException {
        UserDao userDao = new UserDao();
        userDao.createConnection();
        User user = userDao.validate(login, password);
        return user != null;
    }

    public boolean createUser(String login, String password) throws DaoException {
        UserDao userDao = new UserDao();
        userDao.createConnection();
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

    public User getUser(String login) throws DaoException {
        UserDao userDao = new UserDao();
        userDao.createConnection();
        return userDao.findUserByLogin(login);
    }

    public User getUserById(Integer id) throws DaoException {
        UserDao userDao = new UserDao();
        userDao.createConnection();
        return userDao.findUserById(id);
    }
}
