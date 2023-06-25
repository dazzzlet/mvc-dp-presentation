package com.netcompany.demo;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.netcompany.demo.controller.*;
import com.netcompany.demo.core.AbstractController;
import com.netcompany.demo.core.ApplicationContext;
import com.netcompany.demo.utils.ConsoleUtils;

import java.io.Console;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {
            ApplicationContext appContext = initiateApplicationContext();
            AbstractController<?> mainController = appContext.getControllerMap().get(MainController.class);
            ConsoleUtils.cleanConsole();
            if (mainController != null) {
                AbstractController.launch(mainController);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ApplicationContext initiateApplicationContext() throws SQLException {
        Console console = System.console();
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:52000/club_management");
        dataSource.setUser("user");
        dataSource.setPassword("user");
        ApplicationContext appContext = new ApplicationContext(console, dataSource);
        initiateControllerMap(appContext);
        return appContext;
    }

    private static void initiateControllerMap(ApplicationContext appContext) {
        appContext.setControllerInstance(MainController.class, new MainController(appContext));
        appContext.setControllerInstance(LoginController.class, new LoginController(appContext));
        appContext.setControllerInstance(DashboardController.class, new DashboardController(appContext));
        appContext.setControllerInstance(ActivityListController.class, new ActivityListController(appContext));
        appContext.setControllerInstance(ActivityController.class, new ActivityController(appContext));
        appContext.setControllerInstance(UserListController.class, new UserListController(appContext));
        appContext.setControllerInstance(UserController.class, new UserController(appContext));
    }
}
