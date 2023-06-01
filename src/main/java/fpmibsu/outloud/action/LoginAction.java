package fpmibsu.outloud.action;

import fpmibsu.outloud.dao.mysql.TransactionFactoryImpl;
import fpmibsu.outloud.entitiy.User;
import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.service.ServiceFactory;
import fpmibsu.outloud.service.UserService;
import fpmibsu.outloud.service.impl.ServiceFactoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.GenericServlet;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class LoginAction extends Act{
    public String n;
    public String password;
    private static Logger logger = LogManager.getLogger(LoginAction.class);


    public void updatedata(HttpServletRequest req){
         n = req.getParameter("username");
         password = req.getParameter("Пароль");
    }
    @Override
    public void doAction(HttpServletRequest req, HttpServletResponse res) {
        try{
            ServiceFactory service = new ServiceFactoryImpl(new TransactionFactoryImpl());
            UserService userService = service.getService(UserService.class);
            User user;
            user = userService.validate(n, password);
            if (user != null){
                logger.log(Level.INFO, "User is successfully logged");
                res.sendRedirect("succsess.jsp");
            }
            else {
                logger.info("Fail while logging");
                res.sendRedirect("index.jsp");
            }

        }catch(PersistentException | IOException  e){
            e.printStackTrace();
    }
    }
}
