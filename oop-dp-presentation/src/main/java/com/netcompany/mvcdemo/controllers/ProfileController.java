package com.netcompany.mvcdemo.controllers;

import com.netcompany.mvcdemo.models.User;
import com.netcompany.mvcdemo.views.ProfileView;

public class ProfileController {
    private User model;
    private ProfileView view;

    private ProfileController(User model, ProfileView view) {
        this.model = model;
        this.view = view;
    }
}
