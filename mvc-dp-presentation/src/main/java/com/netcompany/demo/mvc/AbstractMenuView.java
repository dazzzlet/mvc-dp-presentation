package com.netcompany.demo.mvc;

import com.netcompany.demo.core.AbstractView;
import com.netcompany.demo.core.EventArg;
import com.netcompany.demo.core.MenuItem;
import com.netcompany.demo.event.GeneralMenuEventArg;
import com.netcompany.demo.model.MenuModel;
import com.netcompany.demo.utils.ConsoleUtils;

import java.io.Console;
import java.util.regex.Pattern;

public abstract class AbstractMenuView<TModel extends MenuModel> extends AbstractView<TModel> {
    private static final Pattern numericPattern = Pattern.compile("^\\d+$");
    private final Console console;
    private boolean isCleanMenu = true;

    public AbstractMenuView(Console console) {
        this.console = console;
    }

    @Override
    public EventArg updateModel(TModel model) {
        MenuModel menuModel = (MenuModel) model;
        String userChoise = null;
        int index;
        int userChoiseInt = 0;
        do {
            if (this.isCleanMenu) {
                ConsoleUtils.cleanConsole();
            }
            System.out.println(menuModel.getHeader());
            index = 1;
            for (MenuItem menuItem : menuModel.getMenuItems()) {
                System.out.println(String.format("%d. %s", index, menuItem.getItemName()));
                index++;
            }
            if (menuModel.getExitItem() != null) {
                System.out.println(String.format("0. %s", menuModel.getExitItem().getItemName()));
            }
            do {
                System.out.print(menuModel.getSelectInstruction());
                userChoise = console.readLine();
            } while (!this.checkUserChoise(userChoise, menuModel));
            userChoiseInt = Integer.parseInt(userChoise);
            if (userChoiseInt != 0) {
                return new GeneralMenuEventArg(
                        menuModel.getMenuItems().get(userChoiseInt - 1)
                );
            }
        } while (userChoiseInt != 0);
        return new GeneralMenuEventArg(menuModel.getExitItem());
    }

    private boolean checkUserChoise(String userChoise, MenuModel menuModel) {
        if (!numericPattern.matcher(userChoise).find()) {
            System.out.println("Invalid choise: the choise must be integer and follow instruction above.");
            return false;
        }
        int userChoiseValue = Integer.parseInt(userChoise);
        int minValue = 0;
        if (menuModel.getExitItem() == null) {
            minValue = 1;
        }
        if (userChoiseValue < minValue || userChoiseValue > menuModel.getMenuItems().size()) {
            System.out.println(
                    String.format("Invalid choise: the choise must between %d and %d"
                            , minValue
                            , menuModel.getMenuItems().size()));
            return false;
        }
        return true;
    }
}
