package com.cam.a3_assignment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Account implements Serializable {
    @SerializedName("_id")
    private String id;
    private String email;
    private String name;
    private String avatar;
    private String pass;
    private Boolean available;

    public Account(String id, String email, String name, String avatar, String pass, Boolean available) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.avatar = avatar;
        this.pass = pass;
        this.available = available;
    }

    public Account(String email, String name, String pass) {
        this.email = email;
        this.name = name;
        this.pass = pass;
    }

    public Account(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPass() {
        return pass;
    }

    public Boolean getAvailable() {
        return available;
    }
}
