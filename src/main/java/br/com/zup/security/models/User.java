package br.com.zup.security.models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @UuidGenerator // Universally Unique Identifier(Identificador Unico Universalmente - Criado para evitar colisoes entre as entidades
    private String uuid;
    private String userName;
    private String password;

    public User(){
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
