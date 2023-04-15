package fpmibsu.outloud.entitiy;
import fpmibsu.outloud.enumfiles.Type;

public class User {
    private Integer id;
    private String name; 
    private String login;
    private String password;
    private Type role;
    private Boolean isConfirmed;

    public User() {}
    public User(User toCopy) {
        this.id = toCopy.getId();
        this.name = toCopy.getName();
        this.isConfirmed = toCopy.isConfirmed;
        this.login = toCopy.getLogin();
        this.password = toCopy.getPassword();
        this.role = toCopy.getRole();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Type role) {
        this.role = role;
    }

    public void setConfimation(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public Type getRole() {
        return this.role;
    }

    public Boolean getConfirmation() {
        return this.isConfirmed;
    }
    public Integer getIntConfirmation() {
        if(this.isConfirmed) {
            return 1;
        } else {
            return 0;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("('").append(name).append("', ");
        stringBuilder.append("'").append(login).append("', ");
        stringBuilder.append("'").append(password).append("', ");
        stringBuilder.append("'").append(role).append("', ");
        stringBuilder.append(getIntConfirmation()).append(")");
        return new String(stringBuilder);
    }

    public String view() {
        StringBuilder stringBuilder = new StringBuilder("User: (id:'");
        stringBuilder.append(id).append("', ");
        stringBuilder.append("name:'").append(name).append("', ");
        stringBuilder.append("login:'").append(login).append("', ");
        stringBuilder.append("pass:'").append(password).append("', ");
        stringBuilder.append("role:'").append(role).append("', confirmation:'");
        stringBuilder.append(getIntConfirmation()).append("')");
        return new String(stringBuilder);
    }
}
