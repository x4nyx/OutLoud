package by.fpmibsu.outloud.entity;

import java.awt.*;

enum Type {
    USER,
    MODERATOR,
    HELPER
}

public class User {
    int id;
    String userName;
    String login;
    String password;
    Type userRole;
    boolean isConfirmed;
    Image avatar;
}
