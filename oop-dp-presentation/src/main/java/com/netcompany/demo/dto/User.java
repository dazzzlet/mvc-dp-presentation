package com.netcompany.demo.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements DTO {
    private int id;
    private String username;
    private String firstname;
    private String password;
    private String role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public void loadDataRow(ResultSet rs) throws SQLException {
        this.id = rs.getInt(1);
        this.username = rs.getString(2);
        this.firstname = rs.getString(3);
        this.password = rs.getString(4);
        this.role = rs.getString(5);
        this.bio = rs.getString(6);
    }
}
