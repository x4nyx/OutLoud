package fpmibsu.outloud.controller.servlets;

import fpmibsu.outloud.dao.mysql.TransactionFactoryImpl;
import fpmibsu.outloud.dao.pool.ConnectionPool;
import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.entitiy.Track;
import fpmibsu.outloud.service.ServiceFactory;
import fpmibsu.outloud.service.TrackService;
import fpmibsu.outloud.service.UserService;
import fpmibsu.outloud.service.impl.ServiceFactoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class SearchServlet extends HttpServlet {
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
        try {
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
            String n = req.getParameter("name");
            ServiceFactory service = new ServiceFactoryImpl(new TransactionFactoryImpl());
            TrackService trackService = service.getService(TrackService.class);
            List<Track> tracks;
            tracks = trackService.getTracks(n);
            if (!tracks.isEmpty()) {
                //logger.info("tracks are found");
                resp.sendRedirect("tracks.jsp");
            } else {
                //logger.info("tracks aren't found");
                resp.sendRedirect("succsess.jsp");
            }
        }
        catch (PersistentException e) {
            e.printStackTrace();
        }
    }
}
