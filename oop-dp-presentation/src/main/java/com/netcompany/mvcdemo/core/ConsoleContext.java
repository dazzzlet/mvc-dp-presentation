package com.netcompany.mvcdemo.core;

import com.netcompany.mvcdemo.models.Identity;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class ConsoleContext {
    private Scanner scanner;
    private Connection connection;
    private Identity identity;
    private List<AbstractMenu> menus;

    public ConsoleContext(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setMenus(List<AbstractMenu> menus) {
        this.menus = menus;
    }

    public List<AbstractMenu> getMenus() {
        return menus;
    }

    public void redirect(String menuHeader) {
        for (AbstractMenu menu : menus) {
            if (menu.getMenuHeader().equals(menuHeader)) {
                menu.launch();
                return;
            }
        }
    }
}
