package fpmibsu.outloud.entitiy;

import java.util.List;

public class Album {
    Integer id;
    String name;
    User creator;
    Integer creationDate;
    Boolean isPlayList;
    List<Track> trackList;
}