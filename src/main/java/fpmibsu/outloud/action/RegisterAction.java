package fpmibsu.outloud.action;

import fpmibsu.outloud.dao.mysql.TransactionFactoryImpl;
import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.service.ServiceFactory;
import fpmibsu.outloud.service.UserService;
import fpmibsu.outloud.service.impl.ServiceFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class RegisterAction extends Act{
    public String login;
    public String password;
    private static Logger logger = LogManager.getLogger(RegisterAction.class);
    public void updatedata(HttpServletRequest req){
        login  = req.getParameter("login");
        password = req.getParameter("password");
    }
    @Override
    public void doAction(HttpServletRequest req, HttpServletResponse res) {
        try{
            ServiceFactory service = new ServiceFactoryImpl(new TransactionFactoryImpl());
            UserService userService = service.getService(UserService.class);
            boolean check = userService.createUser(login, password);
            if (check){
                logger.info(String.format("user \"%s\" is logged", login, req.getRemoteAddr(), req.getRemoteHost(), req.getRemotePort()));
                res.sendRedirect("index.jsp");
            }
            else {
                logger.info(String.format("user \"%s\" unsuccessfully tried to register", login, req.getRemoteAddr(), req.getRemoteHost(), req.getRemotePort()));
                res.sendRedirect("register.jsp");
            }
        }
        catch(PersistentException | IOException e){
            e.printStackTrace();
        }

    }
    }

