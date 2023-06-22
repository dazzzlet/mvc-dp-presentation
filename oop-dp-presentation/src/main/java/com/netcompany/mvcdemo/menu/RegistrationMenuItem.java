package com.netcompany.mvcdemo.menu;

import java.util.Scanner;

import com.netcompany.mvcdemo.core.ConsoleContext;
import com.netcompany.mvcdemo.core.MenuItem;
import com.netcompany.mvcdemo.models.Identity;
import com.netcompany.mvcdemo.models.User;

import static com.netcompany.mvcdemo.utils.Constants.APPLICATION_HEADER;


public class RegistrationMenuItem implements MenuItem {
    private final ConsoleContext appCtx;
    // private final UserService userService;

    public RegistrationMenuItem(ConsoleContext appCtx) {
        this.appCtx = appCtx;
        // this.userService = new UserService(appCtx.getConnection());
    }

    @Override
    public String getItemName() {
        return "Registration";
    }

    @Override
    public void launch() {
        Scanner scanner = this.appCtx.getScanner();
        do {
            System.out.println(APPLICATION_HEADER);
            System.out.println("           REGISTRATION");
            System.out.println();
            Identity identity = this.register(scanner);
            if (identity != null) {
                this.appCtx.setIdentity(identity);
                return;
            }
            System.out.println("Username or password is invalid. Please tried again!");
            scanner.nextLine();
        } while (true);
    }

    private Identity register(Scanner scanner) {
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        User user = new User();
        // User user = this.userService.getUserByUsernamePassword(userName, password);
        if (user != null) {
            Identity identity = new Identity(user);
            return identity;
        }
        return null;
    }
}