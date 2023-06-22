package com.netcompany.mvcdemo;

import java.util.Scanner;

import com.netcompany.mvcdemo.core.ConsoleContext;
import com.netcompany.mvcdemo.core.Menu;
import com.netcompany.mvcdemo.views.AuthenticationView;

public class App {
    public static void main(String[] args) {
        ConsoleContext appContext = initiateApplicationContext();
        Menu application = new AuthenticationView(appContext);
        application.launch();
    }

    private static ConsoleContext initiateApplicationContext() {
        Scanner scanner = new Scanner(System.in);
        ConsoleContext appContext = new ConsoleContext(scanner);
        return appContext;
    }
}
