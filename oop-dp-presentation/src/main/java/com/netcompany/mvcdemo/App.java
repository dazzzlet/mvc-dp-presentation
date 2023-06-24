package com.netcompany.mvcdemo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import com.netcompany.mvcdemo.core.AbstractMenu;
import com.netcompany.mvcdemo.core.ConsoleContext;
import com.netcompany.mvcdemo.core.Menu;
import com.netcompany.mvcdemo.views.AuthenticationView;
import com.netcompany.mvcdemo.views.DashboardView;
import com.mysql.cj.jdbc.MysqlDataSource;

public class App {
    public static void main(String[] args) {
        ConsoleContext appContext = initiateApplicationContext();
        Menu application = new AuthenticationView(appContext);
        application.launch();
    }

    private static ConsoleContext initiateApplicationContext() {
        Scanner scanner = new Scanner(System.in);
        Connection connection = intializeConnection();
        if (connection == null) {
            System.exit(0);
        }
        ConsoleContext appContext = new ConsoleContext(scanner, connection);
        AbstractMenu authenticationView = new AuthenticationView(appContext);
        AbstractMenu dashboardView = new DashboardView(appContext);
        appContext.setMenus(Arrays.asList(new AbstractMenu[]{authenticationView, dashboardView}));

        return appContext;
    }

    private static Connection intializeConnection() {
        Connection connection;
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:52000/club_management");
        dataSource.setUser("user");
        dataSource.setPassword("user");

        try {
            connection = dataSource.getConnection();
        } catch (SQLException ex) {
            return null;
        }

        return connection;
    }
}
