package com.netcompany.mvcdemo.utils;

import java.io.IOException;

public class ConsoleUtils {
    public static void cleanConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException e) {
        }
    }
}
