package com.netcompany.mvcdemo.models;

import com.netcompany.mvcdemo.enums.UserRole;

public class Identity {
    private int id;
    private String userName;
    private String fullName;
    private UserRole role;

    public Identity(User user) {
        this.userName = user.getUsername();
        this.fullName = user.getFirstName() + " " + user.getLastName();
        this.role = UserRole.getUserRole(user.getRole());
        this.id = user.getId();
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

    public int getId() {
        return id;
    }
}
