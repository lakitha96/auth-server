package com.example.authserver.model;

import java.io.Serializable;

/**
 * @author Lakitha Prabudh on 5/11/20
 */
public class AuthTokenResponse implements Serializable {

    private String refresh_token;
    private String access_token;

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}