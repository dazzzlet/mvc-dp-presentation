package com.netcompany.demo.menu.member;

import com.netcompany.demo.core.AbstractAuthorizedMenu;
import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.core.MenuItem;
import com.netcompany.demo.menu.organizer.OrganizerListActivityMenu;

import static com.netcompany.demo.utils.Constants.APPLICATION_HEADER;

public class MemberDashboardMenu extends AbstractAuthorizedMenu implements MenuItem {
    public MemberDashboardMenu(ConsoleContext appCtx) {
        super(appCtx);
        this.menuItems.add(new MemberListActivityMenu(appCtx));
    }

    @Override
    public String getMenuHeader() {
        return String.format("%s\n\t\tWelcome %s\nMember dashboard", APPLICATION_HEADER, this.getUserFullName());
    }

    @Override
    public String getBackItemName() {
        return "Exit";
    }

    @Override
    public String getItemName() {
        return "Member dashboard";
    }
}
