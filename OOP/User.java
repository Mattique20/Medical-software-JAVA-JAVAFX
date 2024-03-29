package com.example.temp.OOP;

public class User {

    private int userID;
    private String username;
    private String password;
    private String name;

    public User(int userID, String username, String password, String name) { // Default Constructor
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
