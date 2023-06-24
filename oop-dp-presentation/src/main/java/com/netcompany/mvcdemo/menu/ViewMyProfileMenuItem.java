package com.netcompany.mvcdemo.menu;

import java.util.Scanner;

import com.netcompany.mvcdemo.core.ConsoleContext;
import com.netcompany.mvcdemo.core.MenuItem;
import com.netcompany.mvcdemo.models.Identity;
import com.netcompany.mvcdemo.models.User;
import com.netcompany.mvcdemo.service.UserService;

import static com.netcompany.mvcdemo.utils.Constants.APPLICATION_HEADER;

public class ViewMyProfileMenuItem implements MenuItem {
    private final ConsoleContext appCtx;
    private final UserService userService;
    public ViewMyProfileMenuItem(ConsoleContext appCtx) {
        this.appCtx = appCtx;
        this.userService = new UserService(appCtx.getConnection());
    }

    @Override
    public String getItemName() {
        return "View My Profile";
    }

    @Override
    public void launch() {
        Scanner scanner = this.appCtx.getScanner();
        System.out.println(APPLICATION_HEADER);
        System.out.println("           VIEW MY PROFILE");
        System.out.println();
        Identity identity = appCtx.getIdentity();
        if (identity == null) {
            return;
        }
        this.printUserDetails(scanner);
        this.appCtx.redirect("Dashboard");
    }

    private void printUserDetails(Scanner scanner) {
        Identity identity = appCtx.getIdentity();
        User user = this.userService.getUserById(identity.getId());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Full name: " + user.getFirstName());
    }
}
