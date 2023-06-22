package com.netcompany.oopdemo.enums;


public enum UserRole {
    Organizer("Organizer"),
    Member("Member");

    private String roleValue;

    private UserRole(String roleValue) {
        this.roleValue = roleValue;
    }

    public String getRoleValue() {
        return roleValue;
    }

    public static UserRole getUserRole(String roleName) {
        switch (roleName) {
            case "Organizer":
                return UserRole.Organizer;
            case "Member":
                return UserRole.Member;
        }
        return null;
    }
}
