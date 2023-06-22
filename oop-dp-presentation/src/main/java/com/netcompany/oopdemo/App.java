package com.netcompany.oopdemo;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.jdbc.MysqlDataSourceFactory;
import com.netcompany.oopdemo.core.ConsoleContext;
import com.netcompany.oopdemo.menu.MainMenu;

import javax.sql.DataSource;
import java.io.Console;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {
            ConsoleContext appContext = initiateApplicationContext();
            MainMenu application = new MainMenu(appContext);
            application.launch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ConsoleContext initiateApplicationContext() throws SQLException {
        Console console = System.console();
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:52000/club_management");
        dataSource.setUser("user");
        dataSource.setPassword("user");
        ConsoleContext appContext = new ConsoleContext(console, dataSource);
        return appContext;
    }
}
