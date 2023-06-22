package com.netcompany.oopdemo.menu;

import java.util.List;

import com.netcompany.oopdemo.core.ConsoleContext;
import com.netcompany.oopdemo.core.Menu;
import com.netcompany.oopdemo.core.MenuItem;
import com.netcompany.oopdemo.menu.member.MemberDashboardMenu;
import com.netcompany.oopdemo.menu.organizer.OrganizerDashboardMenu;

public class MainMenu implements Menu {
    private final ConsoleContext appCtx;
    private final OrganizerDashboardMenu organizerDashboard;
    private final MemberDashboardMenu memberDashboard;
    private LoginMenu loginMenu;

    public MainMenu(ConsoleContext appCtx) {
        this.loginMenu = new LoginMenu(appCtx);
        this.organizerDashboard = new OrganizerDashboardMenu(appCtx);
        this.memberDashboard = new MemberDashboardMenu(appCtx);
        this.appCtx = appCtx;
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
        this.loginMenu.launch();
        if (this.appCtx.getIdentity() != null) {
            switch (this.appCtx.getIdentity().getRole()) {
                case Member:
                    this.launchMemberDashboard();
                    break;
                case Organizer:
                    this.launchOrganizerDashboard();
                    break;
            }
        }
    }

    private void launchOrganizerDashboard() {
        organizerDashboard.launch();
    }

    private void launchMemberDashboard() {
        this.memberDashboard.launch();
    }
}
