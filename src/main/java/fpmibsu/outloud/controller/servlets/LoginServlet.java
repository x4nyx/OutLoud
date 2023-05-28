package fpmibsu.outloud.controller.servlets;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String n = req.getParameter("username");
        String n2 = req.getParameter("Пароль");
        PrintWriter pr = resp.getWriter();

        resp.setContentType("text/html");
        if (Objects.equals(n, "qwerty") && Objects.equals(n2, "qwerty")){
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.include(req, resp);
        }
        else {
            resp.sendRedirect("succsess.jsp");
        }

    }
}

