package com.netcompany.mvcdemo.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.netcompany.mvcdemo.core.AbstractMenu;
import com.netcompany.mvcdemo.core.ConsoleContext;
import com.netcompany.mvcdemo.core.MenuItem;
import com.netcompany.mvcdemo.menu.LoginMenuItem;
import com.netcompany.mvcdemo.menu.RegistrationMenuItem;
import com.netcompany.mvcdemo.models.Identity;

public class DashboardView extends AbstractMenu {
    private Identity identity;

    private List<MenuItem> preLoginMenu = new ArrayList<MenuItem>();

    public DashboardView(ConsoleContext appCtx) {
        super(appCtx);
        MenuItem loginMenuItem = new LoginMenuItem(appCtx);
        MenuItem registrationMenuItem = new RegistrationMenuItem(appCtx);

        super.menuItems = new ArrayList<MenuItem>();
        super.menuItems.add(loginMenuItem);
        super.menuItems.add(registrationMenuItem);
        Collections.addAll(this.preLoginMenu, loginMenuItem, registrationMenuItem);
    }

    public String getBackItemName() {
        return null;
    }

    public String getMenuHeader() {
        return "Dashboard";
    }
}