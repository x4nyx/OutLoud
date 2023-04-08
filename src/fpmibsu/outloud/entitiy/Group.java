package fpmibsu.outloud.entitiy;

import java.util.List;

public class Group {
    Integer id;
    User creator;
    List<User> members;
    Integer userNum;
    Boolean isConfirmed;
    String description;
    String name;
}
