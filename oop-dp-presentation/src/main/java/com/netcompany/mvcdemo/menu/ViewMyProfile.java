package com.netcompany.mvcdemo.menu;

import java.util.Scanner;

import com.netcompany.mvcdemo.core.ConsoleContext;
import com.netcompany.mvcdemo.core.MenuItem;
import com.netcompany.mvcdemo.models.Identity;
import com.netcompany.mvcdemo.models.User;

import static com.netcompany.mvcdemo.utils.Constants.APPLICATION_HEADER;

public class ViewMyProfile implements MenuItem {
    private final ConsoleContext appCtx;
    private User user;
    public ViewMyProfile(ConsoleContext appCtx, User user) {
        this.appCtx = appCtx;
        this.user = user;
    }

    @Override
    public String getItemName() {
        return "View My Profile";
    }

    @Override
    public void launch() {
        Scanner scanner = this.appCtx.getScanner();
        do {
            System.out.println(APPLICATION_HEADER);
            System.out.println("           VIEW MY PROFILE");
            System.out.println();
            Identity identity = appCtx.getIdentity();
            if (identity == null) {
                return;
            }

            this.printUserDetails(scanner);

        } while (true);
    }

    private void printUserDetails(Scanner scanner) {
        System.out.println("Username: " + user.getUsername());
        System.out.println("Bio: " + user.getBio());
        System.out.println("Name: " + user.getFirstName());
        System.out.println("Role: " + user.getRole());
    }
}
