package com.netcompany.oopdemo.dto;

import com.netcompany.oopdemo.enums.UserRole;

public class Identity {
    private int userId;
    private String userName;
    private String fullName;
    private UserRole role;

    public Identity(User user) {
        this.userId = user.getId();
        this.userName = user.getUsername();
        this.fullName = user.getFirstname();
        this.role = user.getRole();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRole getRole() {
        return role;
    }
}
