package com.netcompany.demo.menu;

import java.util.List;

import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.core.Menu;
import com.netcompany.demo.core.MenuItem;
import com.netcompany.demo.menu.member.MemberDashboardMenu;
import com.netcompany.demo.menu.organizer.OrganizerDashboardMenu;

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
