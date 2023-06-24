package com.netcompany.mvcdemo.menu;

import java.util.Scanner;

import com.netcompany.mvcdemo.core.ConsoleContext;
import com.netcompany.mvcdemo.core.MenuItem;
import com.netcompany.mvcdemo.models.Identity;
import com.netcompany.mvcdemo.models.User;
import com.netcompany.mvcdemo.service.UserService;

import static com.netcompany.mvcdemo.utils.Constants.APPLICATION_HEADER;


public class RegistrationMenuItem implements MenuItem {
    private final ConsoleContext appCtx;
    private final UserService userService;

    public RegistrationMenuItem(ConsoleContext appCtx) {
        this.appCtx = appCtx;
        this.userService = new UserService(appCtx.getConnection());
    }

    @Override
    public String getItemName() {
        return "Registration";
    }

    @Override
    public void launch() {
        Scanner scanner = this.appCtx.getScanner();
        do {
            System.out.println("           REGISTRATION");
            System.out.println();
            Identity identity = this.register(scanner);
            if (identity != null) {
                this.appCtx.setIdentity(identity);
                return;
            }
            System.out.println("Unable to register the account. Please try again");
            this.appCtx.redirect("Authentication");
        } while (true);
    }

    private Identity register(Scanner scanner) {
        String username;
        do {
            System.out.println("Username: ");
            username = scanner.nextLine();
            boolean isUserExisted = this.userService.isUserExisted(username);
            if (!isUserExisted) {
                break;
            }
            System.out.println("Username is already existed. Please try again");
        } while (true);
 
        System.out.println("Password: ");
        String password = scanner.nextLine();
        System.out.println("First name: ");
        String firstName = scanner.nextLine();
        System.out.println("Role: ");
        String role = scanner.nextLine();
        System.out.println("Bio: ");
        String bio = scanner.nextLine();
        this.userService.createUser(username, password, firstName, role, bio);
        User user = this.userService.getUserByUsernamePassword(username, password);
        if (user != null) {
            Identity identity = new Identity(user);
            return identity;
        }
        return null;
    }
}