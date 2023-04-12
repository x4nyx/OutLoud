package fpmibsu.outloud.entitiy;

public class Post {
    private Integer id;
    private Group group;
    private User creator;
    private Integer viewCount;
    private String text;
    private String title;

    public User getCreator() {
        return this.creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("('").append(id).append("', ");
        stringBuilder.append("'").append(group.getId()).append("', ");
        stringBuilder.append("'").append(creator.getId()).append("', ");
        stringBuilder.append("'").append(viewCount).append("', ");
        stringBuilder.append("'").append(text).append("', ");
        stringBuilder.append("'").append(title).append("')");
        return new String(stringBuilder);
    }
}
