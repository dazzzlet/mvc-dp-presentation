package com.netcompany.mvcdemo.menu;

import java.util.Scanner;

import com.netcompany.mvcdemo.core.ConsoleContext;
import com.netcompany.mvcdemo.core.MenuItem;
import com.netcompany.mvcdemo.models.Identity;
import com.netcompany.mvcdemo.models.User;
import com.netcompany.mvcdemo.service.UserService;

import static com.netcompany.mvcdemo.utils.Constants.APPLICATION_HEADER;

public class UpdateMyProfileMenuItem implements MenuItem {
    private final ConsoleContext appCtx;

    private final UserService userService;

    public UpdateMyProfileMenuItem(ConsoleContext appCtx) {
        this.appCtx = appCtx;
        this.userService = new UserService(appCtx.getConnection());
    }

    @Override
    public String getItemName() {
        return "Update My Profile";
    }

    @Override
    public void launch() {
        Scanner scanner = this.appCtx.getScanner();
        System.out.println(APPLICATION_HEADER);
        System.out.println("           UPDATE MY PROFILE");
        System.out.println();
        Identity identity = appCtx.getIdentity();
        if (identity == null) {
            return;
        }
        this.updateUserProfile(scanner);
        this.appCtx.redirect("Dashboard");
    }

    private void updateUserProfile(Scanner scanner) {
        Identity identity = appCtx.getIdentity();
        System.out.println("First name: ");
        String firstName = scanner.nextLine();
        System.out.println("Bio: ");
        String bio = scanner.nextLine();

        this.userService.updateUser(identity.getId(), firstName, bio);
    }
}
