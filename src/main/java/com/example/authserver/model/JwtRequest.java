package com.example.authserver.model;

import java.io.Serializable;

/**
 * @author Lakitha Prabudh on 5/11/20
 */
public class JwtRequest implements Serializable {
    private String email;
    private String password;

    public JwtRequest() {
    }

    public JwtRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}