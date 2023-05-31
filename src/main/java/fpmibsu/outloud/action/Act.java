package fpmibsu.outloud.action;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class Act {
    public Act(){
    }
    public abstract  void updatedata(HttpServletRequest req);
    public abstract void doAction(HttpServletRequest req, HttpServletResponse res);
}
