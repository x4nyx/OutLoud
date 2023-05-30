package fpmibsu.outloud.service;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.dao.UserDao;
import fpmibsu.outloud.entitiy.User;
import fpmibsu.outloud.enumfiles.Type;

public class UserService {
    UserDao userDao = new UserDao();

    public UserService() {
        userDao.createConnection();
    }

    public boolean isExist(String login) throws DaoException {
        return userDao.findUserByLogin(login) != null;
    }

    public User validate(String login, String password) throws DaoException {
        return userDao.validate(login, password);
    }

    public boolean createUser(String login, String password) throws DaoException {
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
        return userDao.findUserByLogin(login);
    }

    public User getUserById(Integer id) throws DaoException {
        return userDao.findUserById(id);
    }
}
