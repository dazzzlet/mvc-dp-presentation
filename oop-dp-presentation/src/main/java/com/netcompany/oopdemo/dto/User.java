package com.netcompany.oopdemo.dto;

import com.netcompany.oopdemo.enums.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements DTO {
    private int id;
    private String username;
    private String firstname;
    private String password;
    private UserRole role;
    private String bio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public User clone() {
        User newUser = new User();
        newUser.setId(this.id);
        newUser.setUsername(this.username);
        newUser.setFirstname(this.firstname);
        newUser.setPassword(this.password);
        newUser.setRole(this.role);
        newUser.setBio(this.bio);
        return newUser;
    }

    @Override
    public void loadDataRow(ResultSet rs) throws SQLException {
        this.id = rs.getInt(1);
        this.username = rs.getString(2);
        this.firstname = rs.getString(3);
        this.password = rs.getString(4);
        this.role = UserRole.getUserRole(rs.getString(5));
        this.bio = rs.getString(6);
    }
}
