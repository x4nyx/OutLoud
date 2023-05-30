package fpmibsu.outloud.controller.servlets;

import fpmibsu.outloud.dao.mysql.TransactionFactoryImpl;
import fpmibsu.outloud.dao.pool.ConnectionPool;
import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.service.ServiceFactory;
import fpmibsu.outloud.service.UserService;
import fpmibsu.outloud.service.impl.ServiceFactoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class RegistrServlet extends HttpServlet {
    //private static Logger logger = Logger.getLogger(RegistrServlet.class);
    public static final String DB_URL = "jdbc:mysql://localhost:3306/OutLoud";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "pavelka2470";
    public static final String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final int DB_POOL_START_SIZE = 1;
    public static final int DB_POOL_MAX_SIZE = 10;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 1;
    private static Logger logger = LogManager.getLogger(RegistrServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
            String login  = req.getParameter("login");
            String password = req.getParameter("password");
            ServiceFactory service = new ServiceFactoryImpl(new TransactionFactoryImpl());
            UserService userService = service.getService(UserService.class);
            boolean check = userService.createUser(login, password);
            if (check){
                //logger.info(String.format("user \"%s\" is logged in from %s (%s:%s)", login, req.getRemoteAddr(), req.getRemoteHost(), req.getRemotePort()));
                resp.sendRedirect("index.jsp");
            }
            else {
                //logger.info(String.format("user \"%s\" unsuccessfully tried to register in from %s (%s:%s)", login, req.getRemoteAddr(), req.getRemoteHost(), req.getRemotePort()));
                resp.sendRedirect("register.jsp");
            }
        }
        catch(PersistentException e){
            e.printStackTrace();
        }

    }
}
