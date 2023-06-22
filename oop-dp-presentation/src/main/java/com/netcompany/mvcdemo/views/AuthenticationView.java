package com.netcompany.mvcdemo.views;

import java.util.ArrayList;

import com.netcompany.mvcdemo.core.AbstractMenu;
import com.netcompany.mvcdemo.core.ConsoleContext;
import com.netcompany.mvcdemo.core.MenuItem;
import com.netcompany.mvcdemo.menu.LoginMenuItem;
import com.netcompany.mvcdemo.menu.RegistrationMenuItem;

public class AuthenticationView extends AbstractMenu {
    public AuthenticationView(ConsoleContext appCtx) {
        super(appCtx);
        MenuItem loginMenuItem = new LoginMenuItem(appCtx);
        MenuItem registrationMenuItem = new RegistrationMenuItem(appCtx);

        super.menuItems = new ArrayList<MenuItem>();
        super.menuItems.add(loginMenuItem);
        super.menuItems.add(registrationMenuItem);
    }

    public String getBackItemName() {
        return null;
    }

    public String getMenuHeader() {
        return "Authentication";
    }
}
