package com.netcompany.demo.dto;

import com.netcompany.demo.enums.UserRole;

public class Identity {
    private String userName;
    private String fullName;
    private UserRole role;

    public Identity(User user) {
        this.userName = user.getUsername();
        this.fullName = user.getFirstname();
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
