package fpmibsu.outloud.entitiy;
import fpmibsu.outloud.enumfiles.Status;

public class Report {
    private Integer id;
    private User creator;
    private User helper;
    private Status status;
    private String text;
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getHelper() {
        return helper;
    }

    public void setHelper(User helper) {
        this.helper = helper;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        stringBuilder.append("'").append(creator.getId()).append("', ");
        stringBuilder.append("'").append(helper.getId()).append("', ");
        stringBuilder.append("'").append(status.toString()).append("', ");
        stringBuilder.append("'").append(text).append("', ");
        stringBuilder.append("'").append(title).append("')");
        return new String(stringBuilder);
    }
}


