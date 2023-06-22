package com.netcompany.demo.menu.organizer;

import com.netcompany.demo.core.AbstractAuthorizedMenu;
import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.core.MenuItem;

import static com.netcompany.demo.utils.Constants.APPLICATION_HEADER;

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
