package fpmibsu.outloud.test;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.dao.UserDao;
import fpmibsu.outloud.entitiy.User;
import fpmibsu.outloud.enumfiles.Type;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws DaoException {
        User user = new User();
        user.setId(11);
        user.setLogin("pashok");
        user.setName("pashok");
        user.setPassword("1234");
        user.setRole(Type.USER);
        user.setConfimation(Boolean.TRUE);
        UserDao.delete(11);
        List<User> users = UserDao.findAll();;
        for(User us : users) {
            System.out.println(us.toString());
        }
    }
}
