package fpmibsu.outloud.test;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.dao.UserDao;
import fpmibsu.outloud.entitiy.Post;
import fpmibsu.outloud.entitiy.User;
import fpmibsu.outloud.enumfiles.Status;
import fpmibsu.outloud.enumfiles.Type;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static void userTest() throws DaoException {
        User user = new User();
        user.setId(5);
        user.setLogin("pashok");
        user.setName("kukols");
        user.setPassword("1234567");
        user.setRole(Type.USER);
        user.setConfimation(Boolean.TRUE);
        //UserDao.create(user);
        System.out.println(UserDao.isExist(3));
        List<User> users = UserDao.findAll();;
        for(User us : users) {
            System.out.println(us.toString());
        }

    }

    public static void main(String[] args) throws DaoException {
        Status status = Status.CHECKED;
        System.out.println(status.toString());
    }
}
