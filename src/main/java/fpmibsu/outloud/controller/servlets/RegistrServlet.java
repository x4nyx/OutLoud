package fpmibsu.outloud.controller.servlets;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class RegistrServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(RegistrServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String login  = req.getParameter("login");
            String password = req.getParameter("password");
            UserService userService = new UserService();
            boolean check = userService.createUser(login, password);
            if (check){
                logger.info(String.format("user \"%s\" is logged", login, req.getRemoteAddr(), req.getRemoteHost(), req.getRemotePort()));
                resp.sendRedirect("index.jsp");
            }
            else {
                logger.info(String.format("user \"%s\" unsuccessfully tried to register", login, req.getRemoteAddr(), req.getRemoteHost(), req.getRemotePort()));
                resp.sendRedirect("register.jsp");
            }
        }
        catch(DaoException e){
            e.printStackTrace();
        }

    }
}
