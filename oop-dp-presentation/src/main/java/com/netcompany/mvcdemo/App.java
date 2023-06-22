package com.netcompany.mvcdemo;

import java.util.Arrays;
import java.util.Scanner;

import com.netcompany.mvcdemo.core.AbstractMenu;
import com.netcompany.mvcdemo.core.ConsoleContext;
import com.netcompany.mvcdemo.core.Menu;
import com.netcompany.mvcdemo.views.AuthenticationView;
import com.netcompany.mvcdemo.views.DashboardView;

public class App {
    public static void main(String[] args) {
        ConsoleContext appContext = initiateApplicationContext();
        Menu application = new AuthenticationView(appContext);
        application.launch();
    }

    private static ConsoleContext initiateApplicationContext() {
        Scanner scanner = new Scanner(System.in);
        ConsoleContext appContext = new ConsoleContext(scanner);

        AbstractMenu authenticationView = new AuthenticationView(appContext);
        AbstractMenu dashboardView = new DashboardView(appContext);
        appContext.setMenus(Arrays.asList(new AbstractMenu[]{authenticationView, dashboardView}));
        return appContext;
    }
}
