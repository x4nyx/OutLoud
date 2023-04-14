package fpmibsu.outloud.entitiy;

public class Genre {
    private Integer id;
    private String name;

    public Genre() {}

    public Genre(Genre toCopy) {
        this.id = toCopy.getId();
        this.name = toCopy.getName();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("('").append(name).append("')");
        return new String(stringBuilder);
    }

    public String view() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("('").append(id).append("', ");
        stringBuilder.append("'").append(name).append("')");
        return new String(stringBuilder);
    }
}
