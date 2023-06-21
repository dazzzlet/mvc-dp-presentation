package com.netcompany.demo;

import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.menu.MainMenu;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        ConsoleContext appContext = initiateApplicationContext();
        MainMenu application = new MainMenu(appContext);
        application.launch();
    }

    private static ConsoleContext initiateApplicationContext() {
        Scanner scanner = new Scanner(System.in);
        ConsoleContext appContext = new ConsoleContext(scanner);
        return appContext;
    }
}
