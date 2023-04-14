package fpmibsu.outloud.entitiy;

import java.sql.Date;

public class Track {
    private Integer id;
    private User creator;
    private Date date;
    private Genre genre;
    private String name;
    private Integer playsCount;

    public Track() {}

    public Track(Track toCopy) {
        this.id = toCopy.getId();
        this.creator = toCopy.getCreator();
        this.date = toCopy.getDate();
        this.genre = toCopy.getGenre();
        this.name = toCopy.getName();
        this.playsCount = toCopy.getPlaysCount();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setDate(Date date) {this.date = date;}

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlaysCount(Integer playsCount) {
        this.playsCount = playsCount;
    }

    public Integer getId() {
        return this.id;
    }

    public User getCreator() {
        return this.creator;
    }

    public Date getDate() {
        return this.date;
    }

    public Genre getGenre() {
        return this.genre;
    }

    public String getName() {
        return this.name;
    }

    public Integer getPlaysCount() {
        return this.playsCount;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("('").append(creator.getId()).append("', ");
        stringBuilder.append("'").append(date).append("', ");
        stringBuilder.append("'").append(genre.getId()).append("', ");
        stringBuilder.append("'").append(name).append("', ");
        stringBuilder.append("'").append(playsCount).append("')");
        return new String(stringBuilder);
    }

    public String view() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("('").append(id).append("', ");
        stringBuilder.append("'").append(creator.getId()).append("', ");
        stringBuilder.append("'").append(date).append("', ");
        stringBuilder.append("'").append(genre.getId()).append("', ");
        stringBuilder.append("'").append(name).append("', ");
        stringBuilder.append("'").append(playsCount).append("')");
        return new String(stringBuilder);
    }
}
