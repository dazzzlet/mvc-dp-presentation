package com.netcompany.demo.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class AbstractMenu implements Menu {
    private static final Pattern numericPattern = Pattern.compile("^\\d+$");
    protected ConsoleContext appCtx;
    protected List<MenuItem> menuItems;

    public AbstractMenu(ConsoleContext appCtx) {
        this.appCtx = appCtx;
        this.menuItems = new ArrayList<>();
    }

    @Override
    public List<MenuItem> getMenuItems() {
        return this.menuItems;
    }

    @Override
    public void launch() {
        String userChoise = null;
        int index;
        int userChoiseInt = 0;
        Scanner scanner = this.appCtx.getScanner();
        do {
            System.out.println(this.getMenuHeader());
            index = 1;
            for (MenuItem menuItem : this.getMenuItems()) {
                System.out.println(String.format("%d. %s", index, menuItem.getItemName()));
                index++;
            }
            System.out.println(String.format("0. %s", this.getBackItemName()));
            do {
                System.out.print(this.getSelectInstruction());
                userChoise = scanner.nextLine();
            } while (!this.checkUserChoise(userChoise));
            userChoiseInt = Integer.parseInt(userChoise);
            if (userChoiseInt != 0) {
                this.launchMenuItem(userChoiseInt - 1);
            }
        } while (userChoiseInt != 0);
    }

    protected String getSelectInstruction() {
        return "Enter your choise: ";
    }

    protected void launchMenuItem(int index) {
        this.getMenuItems()
                .get(index)
                .launch();
    }

    private boolean checkUserChoise(String userChoise) {
        if (!numericPattern.matcher(userChoise).find()) {
            System.out.println("Invalid choise: the choise must be integer and follow instruction above.");
            return false;
        }
        int userChoiseValue = Integer.parseInt(userChoise);
        if (userChoiseValue < 0 || userChoiseValue > this.getMenuItems().size()) {
            System.out.println(
                    String.format("Invalid choise: the choise must between 0 and %d", this.getMenuItems().size()));
            return false;
        }
        return true;
    }
}
