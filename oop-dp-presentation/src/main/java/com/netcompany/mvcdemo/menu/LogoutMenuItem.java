package com.netcompany.mvcdemo.menu;

import java.util.Scanner;

import com.netcompany.mvcdemo.core.ConsoleContext;
import com.netcompany.mvcdemo.core.MenuItem;
import com.netcompany.mvcdemo.models.Identity;
import com.netcompany.mvcdemo.models.User;

import static com.netcompany.mvcdemo.utils.Constants.APPLICATION_HEADER;

public class LogoutMenuItem implements MenuItem {
    private final ConsoleContext appCtx;
    // private final UserService userService;

    public LogoutMenuItem(ConsoleContext appCtx) {
        this.appCtx = appCtx;
        // this.userService = new UserService(appCtx.getConnection());
    }

    @Override
    public String getItemName() {
        return "Logout";
    }

    @Override
    public void launch() {
        System.out.println(APPLICATION_HEADER);
        System.out.println("           LOGGING OUT");
        System.out.println();

        System.exit(0);
    }
}
