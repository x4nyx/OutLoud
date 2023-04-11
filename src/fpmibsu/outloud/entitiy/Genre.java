package fpmibsu.outloud.entitiy;

public class Genre {
    private Integer id;
    private String name;

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
        stringBuilder.append("(").append(id).append(", ").append(name).append(")");
        return new String(stringBuilder);
    }
}
