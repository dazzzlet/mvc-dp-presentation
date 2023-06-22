package com.netcompany.demo.menu;

import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.core.MenuItem;
import com.netcompany.demo.dto.Identity;
import com.netcompany.demo.dto.User;
import com.netcompany.demo.repository.UserRepository;
import com.netcompany.demo.service.UserService;

import java.io.Console;
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
        Console console = this.appCtx.getConsole();
        do {
            System.out.println(APPLICATION_HEADER);
            System.out.println("           LOGIN");
            System.out.println();
            Identity identity = this.loginByUsernamePassword(console);
            if (identity != null) {
                this.appCtx.setIdentity(identity);
                return;
            }
            System.out.println("Username or password is invalid. Please tried again!");
            console.readLine();
        } while (true);
    }

    private Identity loginByUsernamePassword(Console console) {
        System.out.print("Username: ");
        String userName = console.readLine();
        String password = String.valueOf(console.readPassword("Password: "));
        User user = this.userService.getUserByUsernamePassword(userName, password);
        if (user != null) {
            Identity identity = new Identity(user);
            return identity;
        }
        return null;
    }
}
