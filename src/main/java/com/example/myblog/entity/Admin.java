package com.example.myblog.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;


public class Admin {
    private Integer userId;
    private String userName;
    private String userCount;
    private String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCount() {
        return userCount;
    }

    public void setUserCount(String userCount) {
        this.userCount = userCount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userCount='" + userCount + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
