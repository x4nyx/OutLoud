package fpmibsu.outloud.controller.servlets;

import fpmibsu.outloud.action.Act;
import fpmibsu.outloud.dao.pool.ConnectionPool;
import fpmibsu.outloud.exception.PersistentException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class MainServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(MainServlet.class);
    public static final String DB_URL = "jdbc:mysql://localhost:3306/OutLoud";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "1234567890";
    public static final String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final int DB_POOL_START_SIZE = 1;
    public static final int DB_POOL_MAX_SIZE = 10;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 1;
    public void init() {
        try {
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
        } catch(PersistentException e) {
            destroy();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Act action = (Act) request.getAttribute("action");
        if(action == null) {
            return;
        }
        action.doAction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {;
        Act action = (Act) request.getAttribute("action");
        if(action == null) {
            return;
        }
        action.doAction(request, response);
    }
}
