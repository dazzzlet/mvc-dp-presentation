package com.netcompany.mvcdemo.models;

import com.netcompany.mvcdemo.enums.UserRole;

public class Identity {
    private String userName;
    private String fullName;
    private UserRole role;

    public Identity(User user) {
        this.userName = user.getUsername();
        this.fullName = user.getFirstName() + " " + user.getLastName();
        this.role = UserRole.getUserRole(user.getRole());
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
