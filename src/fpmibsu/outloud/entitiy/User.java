package fpmibsu.outloud.entitiy;
import fpmibsu.outloud.enumfiles.Type;

public class User {
    private Integer id;
    private String name; 
    private String login;
    private String password;
    private Type role;
    private Boolean isConfirmed;

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
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Type getRole() {
        return role;
    }

    public Boolean getConfirmation() {
        return isConfirmed;
    }
}
