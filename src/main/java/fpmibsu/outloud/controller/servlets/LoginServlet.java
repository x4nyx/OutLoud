package fpmibsu.outloud.controller.servlets;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.entitiy.User;
import fpmibsu.outloud.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//import org.apache.log4j.Logger;

import java.io.IOException;
public class LoginServlet extends HttpServlet {
    //private static Logger logger = Logger.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String n = req.getParameter("username");
            String password = req.getParameter("Пароль");
            UserService userService = new UserService();
            User user;
            user = user = userService.validate(n, password);
            if (user != null){
                HttpSession session = req.getSession();
                session.setAttribute("authorizedUser", user);
                //logger.info(String.format("user \"%s\" is logged in from %s (%s:%s)", n, req.getRemoteAddr(), req.getRemoteHost(), req.getRemotePort()));
                resp.sendRedirect("succsess.jsp");
            }
            else {
                //logger.info(String.format("user \"%s\" unsuccessfully tried to log in from %s (%s:%s)", n, req.getRemoteAddr(), req.getRemoteHost(), req.getRemotePort()));
                resp.sendRedirect("index.jsp");
            }

        }catch(DaoException e){
            e.printStackTrace();
        }
    }
}

