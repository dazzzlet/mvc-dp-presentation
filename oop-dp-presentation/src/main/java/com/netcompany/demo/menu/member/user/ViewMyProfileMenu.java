package com.netcompany.demo.menu.member.user;

import java.util.List;

import com.netcompany.demo.core.AbstractAuthorizedMenu;
import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.core.MenuItem;
import com.netcompany.demo.dto.Register;
import com.netcompany.demo.dto.User;
import com.netcompany.demo.menu.common.AbstractUserMenuItem;
import com.netcompany.demo.service.ActivityService;
import com.netcompany.demo.service.UserService;

public class ViewMyProfileMenu extends AbstractAuthorizedMenu implements MenuItem {
    private final UserService userService;
    private final ActivityService activityService;
    private User user;
    private List<Register> registerRecords;

    public ViewMyProfileMenu(ConsoleContext appCtx) {
        super(appCtx);
        this.userService = new UserService(appCtx.getConnection());
        this.activityService = new ActivityService(appCtx.getConnection());
        this.menuItems.add(new UpdateProfileMenuItem(appCtx));
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void launch() {
        this.setUser(
                this.userService.getUserById(
                        this.appCtx.getIdentity().getUserId()
                )
        );
        this.registerRecords = this.activityService.getAllRegisteredActivityForUser(this.appCtx.getIdentity().getUserId());
        super.launch();
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
                    "My profile\n" +
                            "      ---\n\n" +
                            "Username: %s\n" +
                            "First name: %s\n" +
                            "Bio: %s\n" +
                            "Number of signed up activities: %d\n",
                    this.getUser().getUsername(),
                    this.getUser().getFirstname(),
                    this.getUser().getBio(),
                    this.registerRecords.size()
            );
        }
        return "";
    }

    @Override
    public String getBackItemName() {
        return "Back to dashboard";
    }

    @Override
    public String getItemName() {
        return "View my profile";
    }
}
