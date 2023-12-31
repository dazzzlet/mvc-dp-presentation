package com.netcompany.demo.menu.organizer.user;

import java.util.List;

import com.netcompany.demo.core.AbstractAuthorizedMenu;
import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.core.MenuItem;
import com.netcompany.demo.dto.Activity;
import com.netcompany.demo.dto.Register;
import com.netcompany.demo.dto.User;
import com.netcompany.demo.menu.common.AbstractActivityMenuItem;
import com.netcompany.demo.menu.common.AbstractUserMenuItem;
import com.netcompany.demo.service.ActivityService;
import com.netcompany.demo.service.UserService;
import com.netcompany.demo.utils.StringUtils;

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
            userMenuItem.setUser(this.getUser());
            userMenuItem.launch();
            this.setUser(userMenuItem.getUser());
        } else {
            super.launchMenuItem(index);
        }
    }

    @Override
    public String getMenuHeader() {
        if (this.getUser() != null) {
            return String.format(
                    "User detail\n" +
                            "      ---\n\n" +
                            "ID: %d\n" +
                            "Username: %s\n" +
                            "First name: %s\n" +
                            "Bio: %s\n" +
                            "Role: %s\n" +
                            "Number of signed up activities: %d\n",
                    this.getUser().getId(),
                    this.getUser().getUsername(),
                    this.getUser().getFirstname(),
                    this.getUser().getBio(),
                    this.getUser().getRole().name(),
                    this.registerRecords.size()
            );
        }
        return "";
    }

    @Override
    public String getBackItemName() {
        return "Back to list users";
    }
}
