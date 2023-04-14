package fpmibsu.outloud.entitiy;

import java.util.List;

public class Group {
    private Integer id;
    private User creator;
    private List<User> members;
    private Integer userNum;
    private Boolean isConfirmed;
    private String description;
    private String name;

    public Group() {}

    public Group(Group toCopy) {
        this.id = toCopy.getId();
        this.creator = toCopy.getCreator();
        this.members = toCopy.getMembers();
        this.userNum = toCopy.getUserNum();
        this.isConfirmed = toCopy.getIsConfirmed();
        this.description = toCopy.getDescription();
        this.name = toCopy.getName();
    }

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

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }
    public int getIntConfirmation() {
        if(isConfirmed) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("('").append(creator.getId()).append("', ");
        stringBuilder.append("'").append(userNum).append("', ");
        stringBuilder.append("'").append(getIntConfirmation()).append("', ");
        stringBuilder.append("'").append(description).append("', ");
        stringBuilder.append("'").append(name).append("')");
        return new String(stringBuilder);
    }

    public String view() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("('").append(id).append("', ");
        stringBuilder.append("'").append(creator.getId()).append("', ");
        stringBuilder.append("'").append(userNum).append("', ");
        stringBuilder.append("'").append(getIntConfirmation()).append("', ");
        stringBuilder.append("'").append(description).append("', ");
        stringBuilder.append("'").append(name).append("')");
        return new String(stringBuilder);
    }
}
