package fpmibsu.outloud.entitiy;

public class Post implements Entity{
    private Integer id;
    private Group group;
    private User creator;
    private Integer viewCount;
    private String text;
    private String title;

    public Post() {}

    public Post(Post toCopy) {
        this.id = toCopy.getId();
        this.group = toCopy.getGroup();
        this.creator = toCopy.getCreator();
        this.viewCount = toCopy.getViewCount();
        this.text = toCopy.getText();
        this.title = toCopy.getTitle();
    }

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
        stringBuilder.append("('").append(group.getId()).append("', ");
        stringBuilder.append("'").append(creator.getId()).append("', ");
        stringBuilder.append("'").append(viewCount).append("', ");
        stringBuilder.append("'").append(text).append("', ");
        stringBuilder.append("'").append(title).append("')");
        return new String(stringBuilder);
    }

    public String view() {
        StringBuilder stringBuilder = new StringBuilder("Post: ");
        stringBuilder.append("(id:'").append(id).append("', ");
        stringBuilder.append("groupid:'").append(group.getId()).append("', ");
        stringBuilder.append("creatorid:'").append(creator.getId()).append("', ");
        stringBuilder.append("viewCount:'").append(viewCount).append("', ");
        stringBuilder.append("text:'").append(text).append("', ");
        stringBuilder.append("title:'").append(title).append("')");
        return new String(stringBuilder);
    }
}
