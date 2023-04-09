package fpmibsu.outloud.entitiy;

import java.util.List;

public class Album {
    private Integer id;
    private String name;
    private User creator;
    private Integer creationDate;
    private Boolean isPlayList;
    private List<Track> trackList;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
    
    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public void setPlaylist(Boolean isPlaylist) {
        this.isPlayList = isPlaylist;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public User getCreator() {
        return this.creator;
    }

    public Integer getCreationDate() {
        return this.creationDate;
    }

    public Boolean getPlaylist() {
        return this.isPlayList;
    }

    public List<Track> getTrackList() {
        return this.trackList;
    }
}