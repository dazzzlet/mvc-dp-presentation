package com.netcompany.oopdemo.menu.organizer;

import static com.netcompany.oopdemo.utils.Constants.APPLICATION_HEADER;

import com.netcompany.oopdemo.core.AbstractAuthorizedMenu;
import com.netcompany.oopdemo.core.ConsoleContext;
import com.netcompany.oopdemo.core.MenuItem;

public class OrganizerDashboardMenu extends AbstractAuthorizedMenu implements MenuItem {
    public OrganizerDashboardMenu(ConsoleContext appCtx) {
        super(appCtx);
        this.menuItems.add(new OrganizerListActivityMenu(appCtx));
    }

    @Override
    public String getMenuHeader() {
        return String.format("%s\n\t\tWelcome %s\nOrganizer dashboard", APPLICATION_HEADER, this.getUserFullName());
    }

    @Override
    public String getBackItemName() {
        return "Exit";
    }

    @Override
    public String getItemName() {
        return "Organizer dashboard";
    }
}
