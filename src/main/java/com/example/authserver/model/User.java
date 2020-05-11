package com.example.authserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author Lakitha Prabudh on 5/11/20
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private String uuid;
    @Column
    @JsonIgnore
    private String role;
    @Column
    private String email;

    public User() {
    }

    public User(User user) {
        this.setUsername(user.getUsername());
        this.setEmail(user.getEmail());
        this.setRole(user.getRole());
        this.setPassword(user.getPassword());
        this.setUuid(user.getUuid());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}