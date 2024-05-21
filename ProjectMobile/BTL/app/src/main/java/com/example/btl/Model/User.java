package com.example.btl.Model;

import java.io.Serializable;

public class User implements Serializable {
    private int id,isadmin;
    private String username,password;

    public User() {
    }
    public User( int isadmin, String username, String password) {
        this.isadmin = isadmin;
        this.username = username;
        this.password = password;
    }

    public User(int id, int isadmin, String username, String password) {
        this.id = id;
        this.isadmin = isadmin;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(int isadmin) {
        this.isadmin = isadmin;
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
}
