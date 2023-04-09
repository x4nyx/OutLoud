package fpmibsu.outloud.entitiy;

public class Track {
    private Integer id;
    private User creator;
    private Integer date;
    private Genre genre;
    private String name;
    private Integer playsCount;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

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

    public Integer getDate() {
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
}
