package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {
    public User(String login, String hash, Boolean isAdmin) {
        this.login = login;
        this.hash = hash;
        this.isAdmin = isAdmin;
    }

    public User() {

    }

    @Override
    public String toString() {
        return login;
    }

    public String getLogin() {
        return login;
    }

    public String getHash() {
        return hash;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Id @Column(name = "login", nullable = false)
    protected String login;

    @Column(name = "hash", nullable = false)
    protected String hash;

    @Column(name = "isAdmin")
    protected Boolean isAdmin;
}
