package com.netcompany.mvcdemo.core;

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
        String userChoice = null;
        int index;
        int userChoiceInt = 0;
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
                userChoice = scanner.nextLine();
            } while (!this.checkUserChoice(userChoice));
            userChoiceInt = Integer.parseInt(userChoice);
            if (userChoiceInt != 0) {
                this.launchMenuItem(userChoiceInt - 1);
            }
        } while (userChoiceInt != 0);
    }

    protected String getSelectInstruction() {
        return "Enter your choice: ";
    }

    protected void launchMenuItem(int index) {
        this.getMenuItems()
                .get(index)
                .launch();
    }

    private boolean checkUserChoice(String userChoice) {
        if (!numericPattern.matcher(userChoice).find()) {
            System.out.println("Invalid choice: the choice must be integer and follow instruction above.");
            return false;
        }
        int userChoiceValue = Integer.parseInt(userChoice);
        if (userChoiceValue < 0 || userChoiceValue > this.getMenuItems().size()) {
            System.out.println(
                    String.format("Invalid choice: the choice must between 0 and %d", this.getMenuItems().size()));
            return false;
        }
        return true;
    }
}
