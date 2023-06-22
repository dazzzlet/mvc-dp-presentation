package com.netcompany.oopdemo.menu.organizer;

import java.util.List;

import com.netcompany.oopdemo.core.AbstractAuthorizedMenu;
import com.netcompany.oopdemo.core.ConsoleContext;
import com.netcompany.oopdemo.core.MenuItem;
import com.netcompany.oopdemo.dto.Activity;
import com.netcompany.oopdemo.dto.Register;
import com.netcompany.oopdemo.dto.User;
import com.netcompany.oopdemo.menu.common.AbstractActivityMenuItem;
import com.netcompany.oopdemo.service.ActivityService;
import com.netcompany.oopdemo.service.UserService;
import com.netcompany.oopdemo.utils.StringUtils;

public class OrganizerViewActivityMenu extends AbstractAuthorizedMenu {
    private final UserService userService;
    private final ActivityService activityService;
    private Activity activity;
    private List<Register> registerRecords;

    public OrganizerViewActivityMenu(ConsoleContext appCtx) {
        super(appCtx);
        this.userService = new UserService(appCtx.getConnection());
        this.activityService = new ActivityService(appCtx.getConnection());
        this.menuItems.add(new UpdateActivityMenuItem(appCtx));

    }

    public void setActivity(Activity activity) {
        this.registerRecords = activityService.getAllRegisteredUserForActivity(activity.getId());
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    @Override
    protected void launchMenuItem(int index) {
        MenuItem menuItem = this.menuItems.get(index);
        if (menuItem instanceof AbstractActivityMenuItem) {
            AbstractActivityMenuItem activityMenuItem = (AbstractActivityMenuItem) menuItem;
            activityMenuItem.setActivity(this.activity);
            activityMenuItem.launch();
            this.activity = activityMenuItem.getActivity();
        } else {
            super.launchMenuItem(index);
        }
    }

    @Override
    public String getMenuHeader() {
        if (this.activity != null) {
            User createdByUser = this.userService.getUserById(this.activity.getCreatedBy());
            User updatedByUser = this.userService.getUserById(this.activity.getUpdatedBy());
            String createdByUserName = createdByUser.getFirstname();
            String createdOn = StringUtils.formatDate(this.activity.getCreatedOnValue(), "dd MMM, YYYY");
            String date = StringUtils.formatDate(this.activity.getDateValue(), "dd MMM, YYYY");
            String updatedByUserName = "n/a";
            String updatedOn = "n/a";
            if (updatedByUser != null) {
                updatedByUserName = updatedByUser.getFirstname();
                updatedOn = StringUtils.formatDate(this.activity.getUpdatedOnValue(), "dd MMM, YYYY");
            }
            return String.format(
                    "Activity detail\n" +
                            "      ---\n\n" +
                            "Title: %s\n" +
                            "Description: %s\n" +
                            "Date: %s\n" +
                            "Status: %s\n" +
                            "Number of signed up: %d\n" +
                            "Created on: %s\n" +
                            "Created by: %s\n" +
                            "Updated on: %s\n" +
                            "Updated by: %s\n",
                    this.activity.getTitle(),
                    this.activity.getDescription(),
                    date,
                    this.activity.getStatus().name(),
                    this.registerRecords.size(),
                    createdOn,
                    createdByUserName,
                    updatedOn,
                    updatedByUserName
            );
        }
        return "";
    }

    @Override
    public String getBackItemName() {
        return "Back to list activity";
    }
}
