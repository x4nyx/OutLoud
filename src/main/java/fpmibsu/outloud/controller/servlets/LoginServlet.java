package fpmibsu.outloud.controller.servlets;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.entitiy.User;
import fpmibsu.outloud.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
public class LoginServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(LoginServlet.class);
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
                logger.info("User is successfully logged");
                resp.sendRedirect("succsess.jsp");
            }
            else {
                logger.info("Fail while logging");
                resp.sendRedirect("index.jsp");
            }

        }catch(DaoException e){
            e.printStackTrace();
        }
    }
}

