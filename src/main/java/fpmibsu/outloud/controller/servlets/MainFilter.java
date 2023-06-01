package fpmibsu.outloud.controller.servlets;

import fpmibsu.outloud.action.*;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class MainFilter implements Filter {
    //private static Logger logger = LogManager.getLogger(MainFilter.class);
    private Act action = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(!(servletRequest instanceof HttpServletRequest request)
                || !(servletResponse instanceof HttpServletResponse)) {
            return;
        }
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse res  = (HttpServletResponse)servletResponse;
        try {
            String actionStr = req.getRequestURI().replace(req.getContextPath() + '/', "");
            //logger.info(actionStr);
            switch (actionStr) {
                case "index.jsp", "" -> {
                     action = new LoginAction();

                }
                case "register.jsp" -> {
                    action = new RegisterAction();
                }
                case "succsess.jsp" -> {
                    action = new SearchAction();
                }
            }
            if(action != null) {
                action.updatedata(req);
                req.setAttribute("action", action);

            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
        catch (Exception r) {
            System.out.println("228");
        }
    }

    @Override
    public void destroy() {}
}