package com.netcompany.demo.core;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.netcompany.demo.dto.Identity;

import java.io.Console;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleContext {
    Console console;
    private Connection connection;
    private Identity identity;

    public ConsoleContext(Console console, MysqlDataSource dataSource) throws SQLException {
        this.console = console;
        this.connection = dataSource.getConnection();
    }

    public Console getConsole() {
        return console;
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
