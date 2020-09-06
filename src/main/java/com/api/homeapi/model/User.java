package com.api.homeapi.model;

public class User {

    private final long id;
    private String userName;
    private String passwordHash;

    public User(long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        // Hash using pgcrypto
        this.passwordHash = password;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

}
