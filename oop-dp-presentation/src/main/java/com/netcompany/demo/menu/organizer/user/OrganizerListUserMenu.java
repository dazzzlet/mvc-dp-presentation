package com.netcompany.demo.menu.organizer.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.netcompany.demo.core.AbstractAuthorizedCollectionMenu;
import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.core.MenuItem;
import com.netcompany.demo.dto.Activity;
import com.netcompany.demo.dto.User;
import com.netcompany.demo.service.UserService;
import com.netcompany.demo.utils.StringUtils;

public class OrganizerListUserMenu extends AbstractAuthorizedCollectionMenu implements MenuItem {
    private final ArrayList<MenuItem> activityList;
    private final OrganizerViewUserMenu viewUserMenu;
    private final UserService userService;

    public OrganizerListUserMenu(ConsoleContext appCtx) {
        super(appCtx);
        this.viewUserMenu = new OrganizerViewUserMenu(appCtx);
        this.activityList = new ArrayList<>();
        this.userService = new UserService(appCtx.getConnection());

    }

    @Override
    public void launch() {
        this.initializeData();
        super.launch();
    }

    private void initializeData() {
        this.activityList.clear();
        List<User> users = this.userService.getAllUsers();
        for (User user : users) {
            this.activityList.add(new UserItem(user));
        }
    }

    @Override
    protected List<MenuItem> getCollection() {
        return this.activityList;
    }

    @Override
    protected List<MenuItem> getAdditionalMenuItem() {
        return Collections.emptyList();
    }

    @Override
    protected void launchCollectionItem(MenuItem menuItem) {
        if (menuItem instanceof UserItem) {
            UserItem userItem = (UserItem) menuItem;
            this.viewUserMenu.setUser(userItem.getUser());
            this.viewUserMenu.launch();
            userItem.setUser(this.viewUserMenu.getUser());
        }
    }

    @Override
    protected void launchAdditionalMenuItem(MenuItem menuItem) {

    }

    @Override
    public String getMenuHeader() {
        return "List of user";
    }

    @Override
    public String getBackItemName() {
        return "Back to dashboard";
    }

    @Override
    public String getItemName() {
        return "Manage users";
    }

    private class UserItem implements MenuItem {
        private User user;

        private UserItem(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        @Override
        public String getItemName() {
            return String.format(
                    "%d | [%s] %s"
                    , this.user.getId()
                    , this.user.getRole().name()
                    , this.user.getUsername()
            );
        }

        @Override
        public void launch() {

        }
    }
}
