package fpmibsu.outloud.controller.servlets;

import fpmibsu.outloud.dao.mysql.TransactionFactoryImpl;
import fpmibsu.outloud.dao.pool.ConnectionPool;
import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.entitiy.User;
import fpmibsu.outloud.service.ServiceFactory;
import fpmibsu.outloud.service.UserService;
import fpmibsu.outloud.service.impl.ServiceFactoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
public class LoginServlet extends HttpServlet {
    //private static Logger logger = Logger.getLogger(LoginServlet.class);
    public static final String DB_URL = "jdbc:mysql://localhost:3306/OutLoud";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "pavelka2470";
    public static final String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final int DB_POOL_START_SIZE = 1;
    public static final int DB_POOL_MAX_SIZE = 10;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 1;

    private static Logger logger = LogManager.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
            String n = req.getParameter("username");
            String password = req.getParameter("Пароль");
            ServiceFactory service = new ServiceFactoryImpl(new TransactionFactoryImpl());
            UserService userService = service.getService(UserService.class);
            User user;
            user = user = userService.validate(n, password);
            if (user != null){
                HttpSession session = req.getSession();
                session.setAttribute("authorizedUser", user);
                logger.info("User is successfully logged");
                resp.sendRedirect("succsess.jsp");
            }
            else {
                logger.info("Fail while logging");
                resp.sendRedirect("index.jsp");
            }

        }catch(PersistentException e){
            e.printStackTrace();
        }
    }
}

