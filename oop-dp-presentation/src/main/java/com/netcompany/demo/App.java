package com.netcompany.demo;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.jdbc.MysqlDataSourceFactory;
import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.menu.MainMenu;
import com.netcompany.demo.utils.ConsoleUtils;

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
            ConsoleUtils.cleanConsole();
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
