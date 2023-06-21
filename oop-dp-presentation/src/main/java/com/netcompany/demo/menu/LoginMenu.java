package com.netcompany.demo.menu;

import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.core.MenuItem;
import com.netcompany.demo.dto.Identity;
import com.netcompany.demo.dto.User;
import com.netcompany.demo.repository.UserRepository;
import com.netcompany.demo.service.UserService;

import java.util.Scanner;

import static com.netcompany.demo.utils.Constants.APPLICATION_HEADER;

public class LoginMenu implements MenuItem {
    private final ConsoleContext appCtx;
    private final UserService userService;

    public LoginMenu(ConsoleContext appCtx) {
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
        User user = this.userService.getUserByUsernamePassword(userName, password);
        if (user != null) {
            Identity identity = new Identity(user);
            return identity;
        }
        return null;
    }
}
