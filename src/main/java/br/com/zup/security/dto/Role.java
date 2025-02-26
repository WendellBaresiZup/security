package br.com.zup.security.dto;

public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private final String roleName;

    Role(String roleName){
        this.roleName = roleName;
    }

    public String getRoleName(){
        return this.name();
    }
}
