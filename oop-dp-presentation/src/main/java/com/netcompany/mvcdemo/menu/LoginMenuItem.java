package com.netcompany.mvcdemo.menu;

import java.util.Scanner;

import com.netcompany.mvcdemo.core.ConsoleContext;
import com.netcompany.mvcdemo.core.MenuItem;
import com.netcompany.mvcdemo.models.Identity;
import com.netcompany.mvcdemo.models.User;
// import com.netcompany.mvcdemo.service.UserService;

import static com.netcompany.mvcdemo.utils.Constants.APPLICATION_HEADER;


public class LoginMenuItem implements MenuItem {
    private final ConsoleContext appCtx;
    // private final UserService userService;

    public LoginMenuItem(ConsoleContext appCtx) {
        this.appCtx = appCtx;
        // this.userService = new UserService(appCtx.getConnection());
    }

    @Override
    public String getItemName() {
        return "Login";
    }

    @Override
    public void launch() {
        Scanner scanner = this.appCtx.getScanner();
        do {
            System.out.println(APPLICATION_HEADER);
            System.out.println("           LOGIN");
            System.out.println();
            Identity identity = this.loginByUsernamePassword(scanner);
            if (identity != null) {
                this.appCtx.setIdentity(identity);
                return;
            }
            System.out.println("Username or password is invalid. Please tried again!");
            scanner.nextLine();
        } while (true);
    }

    private Identity loginByUsernamePassword(Scanner scanner) {
        System.out.println("Username: ");
        String userName = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        User user = null;
        // User user = this.userService.getUserByUsernamePassword(userName, password);
        if (user != null) {
            Identity identity = new Identity(user);
            return identity;
        }
        appCtx.redirect("Authentication");
        return null;
    }
}