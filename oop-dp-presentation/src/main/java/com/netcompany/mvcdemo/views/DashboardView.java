package com.netcompany.mvcdemo.views;

import java.util.ArrayList;

import com.netcompany.mvcdemo.core.AbstractMenu;
import com.netcompany.mvcdemo.core.ConsoleContext;
import com.netcompany.mvcdemo.core.MenuItem;
import com.netcompany.mvcdemo.menu.UpdateMyProfileMenuItem;
import com.netcompany.mvcdemo.menu.ViewMyProfileMenuItem;

public class DashboardView extends AbstractMenu {
    public DashboardView(ConsoleContext appCtx) {
        super(appCtx);
        MenuItem viewMyProfileMenuItem = new ViewMyProfileMenuItem(appCtx);
        MenuItem updateMyProfileMenuItem = new UpdateMyProfileMenuItem(appCtx);
        super.menuItems = new ArrayList<MenuItem>();
        super.menuItems.add(viewMyProfileMenuItem);
        super.menuItems.add(updateMyProfileMenuItem);
    }

    public String getBackItemName() {
        return null;
    }

    public String getMenuHeader() {
        return "Dashboard";
    }
}