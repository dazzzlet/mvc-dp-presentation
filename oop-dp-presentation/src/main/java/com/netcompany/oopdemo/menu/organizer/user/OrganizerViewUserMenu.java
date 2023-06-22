package com.netcompany.oopdemo.menu.organizer.user;

import com.netcompany.oopdemo.core.AbstractAuthorizedMenu;
import com.netcompany.oopdemo.core.ConsoleContext;
import com.netcompany.oopdemo.core.MenuItem;
import com.netcompany.oopdemo.dto.Activity;
import com.netcompany.oopdemo.dto.Register;
import com.netcompany.oopdemo.dto.User;
import com.netcompany.oopdemo.menu.common.AbstractActivityMenuItem;
import com.netcompany.oopdemo.menu.common.AbstractUserMenuItem;
import com.netcompany.oopdemo.service.ActivityService;
import com.netcompany.oopdemo.service.UserService;
import com.netcompany.oopdemo.utils.StringUtils;

import java.util.List;

public class OrganizerViewUserMenu extends AbstractAuthorizedMenu {
    private final UserService userService;
    private final ActivityService activityService;
    private User user;
    private List<Register> registerRecords;

    public OrganizerViewUserMenu(ConsoleContext appCtx) {
        super(appCtx);
        this.userService = new UserService(appCtx.getConnection());
        this.activityService = new ActivityService(appCtx.getConnection());
        this.menuItems.add(new UpdateUserMenuItem(appCtx));

    }

    public void setUser(User user) {
        this.registerRecords = activityService.getAllRegisteredActivityForUser(user.getId());
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    protected void launchMenuItem(int index) {
        MenuItem menuItem = this.menuItems.get(index);
        if (menuItem instanceof AbstractUserMenuItem) {
            AbstractUserMenuItem userMenuItem = (AbstractUserMenuItem) menuItem;
            userMenuItem.setUser(this.user);
            userMenuItem.launch();
            this.user = userMenuItem.getUser();
        } else {
            super.launchMenuItem(index);
        }
    }

    @Override
    public String getMenuHeader() {
        if (this.user != null) {
            return String.format(
                    "Activity detail\n" +
                            "      ---\n\n" +
                            "ID: %d\n" +
                            "Username: %s\n" +
                            "First name: %s\n" +
                            "Bio: %s\n" +
                            "Status: %s\n" +
                            "Number of signed up activities: %d\n",
                    this.user.getId(),
                    this.user.getUsername(),
                    this.user.getFirstname(),
                    this.user.getBio(),
                    this.user.getRole().name(),
                    this.registerRecords.size()
            );
        }
        return "";
    }

    @Override
    public String getBackItemName() {
        return "Back to list activity";
    }
}
