package com.netcompany.demo.menu;

import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.core.Menu;
import com.netcompany.demo.core.MenuItem;

import java.util.List;

public class MainMenu implements Menu {
    private LoginMenu loginMenu;

    public MainMenu(ConsoleContext appCtx) {
        this.loginMenu = new LoginMenu(appCtx);
    }

    @Override
    public List<MenuItem> getMenuItems() {
        return null;
    }

    @Override
    public String getMenuHeader() {
        return null;
    }

    @Override
    public String getBackItemName() {
        return null;
    }

    @Override
    public void launch() {

    }
}
