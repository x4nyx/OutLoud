package by.fpmibsu.outloud.entity;

enum Status{
    CHECKED,
    DENIED,
    ACCEPTED,
    WAITED
}

public class Report {
    int id;
    int creatorId;
    Status status;
    String text;
    String title;
}
