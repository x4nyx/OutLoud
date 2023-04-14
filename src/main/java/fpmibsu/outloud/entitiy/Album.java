package fpmibsu.outloud.entitiy;

import java.util.List;
import java.sql.Date;

public class Album {
    private Integer id;
    private String name;
    private User creator;
    private Date creationDate;
    private Boolean isPlayList;
    private List<Track> trackList;

    public Album() {}

    public Album(Album albumToCopy) {
        this.id = albumToCopy.getId();
        this.name = albumToCopy.getName();
        this.isPlayList = albumToCopy.getIsPlaylist();
        this.creator = albumToCopy.getCreator();
        this.creationDate = albumToCopy.getCreationDate();
        this.trackList = albumToCopy.getTrackList();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
    
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setIsPlaylist(Boolean isPlaylist) {
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

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Boolean getIsPlaylist() {
        return this.isPlayList;
    }
    public int getIntIsPlayList() {
        if(this.isPlayList) {
            return 1;
        } else {
            return 0;
        }
    }

    public List<Track> getTrackList() {
        return this.trackList;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("('").append(getName()).append("', ");
        stringBuilder.append("'").append(getIntIsPlayList()).append("', ");
        stringBuilder.append("'").append(getCreator().getId()).append("', ");
        stringBuilder.append("'").append(getCreationDate()).append("') ");
        return new String(stringBuilder);
    }

    public String view() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("('").append(id).append("', ");
        stringBuilder.append("'").append(getName()).append("', ");
        stringBuilder.append("'").append(getIntIsPlayList()).append("', ");
        stringBuilder.append("'").append(getCreator().getId()).append("', ");
        stringBuilder.append("'").append(getCreationDate()).append("') ");
        return new String(stringBuilder);
    }
}