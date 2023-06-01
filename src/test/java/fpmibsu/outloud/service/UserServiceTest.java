package fpmibsu.outloud.service;

import fpmibsu.outloud.dao.mysql.TransactionFactoryImpl;
import fpmibsu.outloud.dao.pool.ConnectionPool;
import fpmibsu.outloud.entitiy.User;
import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.service.impl.ServiceFactoryImpl;
import fpmibsu.outloud.service.impl.UserServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class UserServiceTest {
    @BeforeClass
    void setUp() throws PersistentException {
        ConnectionPool.getInstance().init("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/OutLoud", "LINUX228", "123456", 1, 10, 1);
    }

    @DataProvider(name = "validateProvider")
    public Object[][] validateProvider() {
        return new Object[][] {
                {"pashok", "1234", new User(1, "a", "pashok", "1234", "MODERATOR", 1)},
                {"LAAAAAAAAAAAAAAAAAAAA", "ASSSSSSSSSSSSSSSSSSS", null}
        };
    }

    @DataProvider(name = "existProvider")
    public Object[][] existProvider() {
        return new Object[][] {
                {"pashok", true},
                {"LFAAAAAAAAAAAAAAAALSLFLALFLAFLSALFSALFLASFL", false}
        };
     }

     @DataProvider(name = "getProvider")
     public Object[][] getProvider() {
        return new Object[][] {
                {"pashok", new User(1, "a", "pashok", "1234", "MODERATOR", 1)},
                {"LAAAAAAAAAAAAAAAAAAAA", null}
        };
     }

     @DataProvider(name = "getByIdProvider")
     public Object[][] getByIdProvider() {
        return new Object[][] {
                {1, new User(1, "a", "pashok", "1234", "MODERATOR", 1)},
                {0, null},
                {-123, null}
        };
     }

    @Test (description = "Existable test", dataProvider = "existProvider")
    public void testIsExist(String login, Boolean expectedValue) throws PersistentException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl(new TransactionFactoryImpl());
        UserService userService = serviceFactory.getService(UserService.class);
        Boolean testable = userService.isExist(login);
        Assert.assertEquals(testable, expectedValue);
    }

    @Test (description = "Validate test", dataProvider = "validateProvider")
    public void testValidate(String login, String password, User expectedUser) throws PersistentException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl(new TransactionFactoryImpl());
        UserService userService = serviceFactory.getService(UserService.class);
        User testable = userService.validate(login, password);
        Assert.assertEquals(User.userString(testable), User.userString(expectedUser));
    }

    @Test (description = "Get test", dataProvider = "getProvider")
    public void testGetUser(String login, User expectedUser) throws PersistentException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl(new TransactionFactoryImpl());
        UserService userService = serviceFactory.getService(UserService.class);
        User testable = userService.getUser(login);
        Assert.assertEquals(User.userString(testable), User.userString(expectedUser));
    }

    @Test (description = "Get by id test", dataProvider = "getByIdProvider")
    public void testGetUserById(Integer id, User expectedUser) throws PersistentException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl(new TransactionFactoryImpl());
        UserService userService = serviceFactory.getService(UserService.class);
        User testable = userService.getUserById(id);
        Assert.assertEquals(User.userString(testable), User.userString(expectedUser));
    }
}