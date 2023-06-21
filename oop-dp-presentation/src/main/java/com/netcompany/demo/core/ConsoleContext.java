package com.netcompany.demo.core;

import com.netcompany.demo.dto.Identity;

import java.sql.Connection;
import java.util.Scanner;

public class ConsoleContext {
    private Scanner scanner;
    private Connection connection;
    private Identity identity;

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
}
