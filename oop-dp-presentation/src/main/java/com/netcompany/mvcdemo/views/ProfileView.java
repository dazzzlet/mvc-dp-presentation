package com.netcompany.mvcdemo.views;

import com.netcompany.mvcdemo.models.User;

public class ProfileView {
    public static void printProfileDetail(User user) {
        System.out.println("User Profile Detail: ");
        System.out.println("Name: " + user.getFirstName());
    }
    public static void editProfile() {

    }
}
