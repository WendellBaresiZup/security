package br.com.zup.security.dto;

public class UserDTO {
    private String uuid;
    private String userName;
    private String password;
    private Role role;

    public UserDTO(){

    }

    public UserDTO(String uuid, String userName, String password, Role role) {
        this.uuid = uuid;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
