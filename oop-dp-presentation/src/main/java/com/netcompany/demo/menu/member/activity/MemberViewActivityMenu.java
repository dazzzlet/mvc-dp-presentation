package com.netcompany.demo.menu.member.activity;

import com.netcompany.demo.core.AbstractAuthorizedMenu;
import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.core.MenuItem;
import com.netcompany.demo.dto.Activity;
import com.netcompany.demo.dto.Register;
import com.netcompany.demo.menu.common.AbstractActivityMenuItem;
import com.netcompany.demo.menu.common.RegisterActivityMenuItem;
import com.netcompany.demo.service.ActivityService;
import com.netcompany.demo.service.UserService;
import com.netcompany.demo.utils.StringUtils;

public class MemberViewActivityMenu extends AbstractAuthorizedMenu {
    private final ActivityService activityService;
    private final RegisterActivityMenuItem registerActivityMenu;
    private Activity activity;

    public MemberViewActivityMenu(ConsoleContext appCtx) {
        super(appCtx);
        this.activityService = new ActivityService(appCtx.getConnection());
        this.registerActivityMenu = new RegisterActivityMenuItem(appCtx);
        this.menuItems.add(this.registerActivityMenu);
    }

    public void setActivity(Activity activity) {

        Register registerRecord = this.activityService.getRegisteredUserForActivity(
                this.appCtx.getIdentity().getUserId()
                , activity.getId()
        );
        this.activity = activity;
        this.registerActivityMenu.setActivity(activity);
        this.registerActivityMenu.setRegisterRecord(registerRecord);
    }

    @Override
    protected void launchMenuItem(int index) {
        MenuItem menuItem = this.menuItems.get(index);
        if (menuItem instanceof AbstractActivityMenuItem) {
            AbstractActivityMenuItem activityMenuItem = (AbstractActivityMenuItem) menuItem;
            activityMenuItem.setActivity(this.activity);
            activityMenuItem.launch();
        } else {
            super.launchMenuItem(index);
        }
    }

    @Override
    public String getMenuHeader() {
        if (this.activity != null) {
            String date = StringUtils.formatDate(this.activity.getDateValue(), "dd MMM, YYYY");
            String registerOn = "";
            Register registerRecord = this.registerActivityMenu.getRegisterRecord();
            if (registerRecord != null) {
                registerOn = String.format("Signed up on: %s\n",
                        StringUtils.formatDate(registerRecord.getRegisterOnValue(), "dd MMM, YYYY")
                );
            }
            return String.format(
                    "Activity detail\n" +
                            "      ---\n\n" +
                            "Title: %s\n" +
                            "Description: %s\n" +
                            "Date: %s\n%s",
                    this.activity.getTitle(),
                    this.activity.getDescription(),
                    date,
                    registerOn
            );
        }
        return "";
    }

    @Override
    public String getBackItemName() {
        return "Back to list activity";
    }
}
