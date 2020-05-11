package com.example.authserver.resource;

import com.example.authserver.model.User;

/**
 * @author Lakitha Prabudh on 5/11/20
 */
public class UserResource {
    private String username;
    private String password;
    private String uuid;
    private String role;
    private String email;
    private boolean active;

    public UserResource() {
    }

    public UserResource(UserResource user) {
        this.setUsername(user.getUsername());
        this.setEmail(user.getEmail());
        this.setRole(user.getRole());
        this.setPassword(user.getPassword());
        this.setUuid(user.getUuid());
        this.setActive(user.isActive());
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}