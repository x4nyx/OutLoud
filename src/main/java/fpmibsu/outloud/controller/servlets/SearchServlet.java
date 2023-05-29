package fpmibsu.outloud.controller.servlets;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.entitiy.Track;
import fpmibsu.outloud.service.TrackService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class SearchServlet extends HttpServlet {
    //private static Logger logger = Logger.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String n = req.getParameter("name");
            TrackService trackService = new TrackService();
            List<Track> tracks;
            try {
                tracks = trackService.getTracks(n);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
            if (!tracks.isEmpty()){
                //logger.info("tracks are found");
                resp.sendRedirect("tracks.jsp");
            }

   /*    if (Objects.equals(n, "qwerty") && Objects.equals(n2, "qwerty")){
            logger.info(String.format("user \"%s\" is logged in from %s (%s:%s)", n, req.getRemoteAddr(), req.getRemoteHost(), req.getRemotePort()));
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.include(req, resp);
        } */
        else {
            //logger.info("tracks aren't found");
            RequestDispatcher rd = req.getRequestDispatcher("succsess.jsp");
            rd.include(req, resp);
        }

    }
}
