package com.netcompany.mvcdemo.menu;

import java.util.Scanner;

import com.netcompany.mvcdemo.core.ConsoleContext;
import com.netcompany.mvcdemo.core.MenuItem;
import com.netcompany.mvcdemo.enums.UserRole;
import com.netcompany.mvcdemo.models.Identity;
import com.netcompany.mvcdemo.models.User;
import com.netcompany.mvcdemo.service.UserService;

public class CreateEventMenuItem implements MenuItem {
    private final ConsoleContext appCtx;
    private final UserService userService;

    public CreateEventMenuItem(ConsoleContext appCtx) {
        this.appCtx = appCtx;
        this.userService = new UserService(appCtx.getConnection());
    }

    @Override
    public String getItemName() {
        return "Login";
    }

    @Override
    public void launch() {
        Scanner scanner = this.appCtx.getScanner();
        this.createEvent(scanner);
    }

    private Identity createEvent(Scanner scanner) {
        Identity identity = appCtx.getIdentity();
        if (identity == null || identity.getRole() != UserRole.Organizer) {
            System.out.println("User does not have permission to create event");
            appCtx.redirect("Dashboard");
        }

        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Start time: ");
        String startTime = scanner.nextLine();
        System.out.println("End time: ");
        String endTime = scanner.nextLine();

        return null;
    }
}
