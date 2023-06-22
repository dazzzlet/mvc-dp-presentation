package com.netcompany.oopdemo.menu.member;

import static com.netcompany.oopdemo.utils.Constants.APPLICATION_HEADER;

import com.netcompany.oopdemo.core.AbstractAuthorizedMenu;
import com.netcompany.oopdemo.core.ConsoleContext;
import com.netcompany.oopdemo.core.MenuItem;

public class MemberDashboardMenu extends AbstractAuthorizedMenu implements MenuItem {
    public MemberDashboardMenu(ConsoleContext appCtx) {
        super(appCtx);
        this.menuItems.add(new MemberListActivityMenu(appCtx));
        this.menuItems.add(new ViewMyProfileMenu(appCtx));
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
