package com.netcompany.demo.menu.organizer;

import static com.netcompany.demo.utils.Constants.APPLICATION_HEADER;

import com.netcompany.demo.core.AbstractAuthorizedMenu;
import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.core.MenuItem;
import com.netcompany.demo.menu.organizer.activity.OrganizerListActivityMenu;
import com.netcompany.demo.menu.organizer.user.OrganizerListUserMenu;

public class OrganizerDashboardMenu extends AbstractAuthorizedMenu implements MenuItem {
    public OrganizerDashboardMenu(ConsoleContext appCtx) {
        super(appCtx);
        this.menuItems.add(new OrganizerListActivityMenu(appCtx));
        this.menuItems.add(new OrganizerListUserMenu(appCtx));
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
