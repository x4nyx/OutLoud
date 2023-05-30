package fpmibsu.outloud.controller.servlets;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.entitiy.Track;
import fpmibsu.outloud.service.TrackService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class SearchServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String n = req.getParameter("name");
            TrackService trackService = new TrackService();
            List<Track> tracks;
            tracks = trackService.getTracks(n);
            if (!tracks.isEmpty()) {
                logger.info("tracks are found");
                resp.sendRedirect("tracks.jsp");
            } else {
                logger.info("tracks aren't found");
                resp.sendRedirect("succsess.jsp");
            }
        }
        catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
