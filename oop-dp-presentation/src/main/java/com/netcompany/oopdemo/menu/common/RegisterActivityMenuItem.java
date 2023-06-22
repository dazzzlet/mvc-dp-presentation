package com.netcompany.oopdemo.menu.common;

import com.netcompany.oopdemo.core.ConsoleContext;
import com.netcompany.oopdemo.core.MenuItem;
import com.netcompany.oopdemo.dto.Activity;
import com.netcompany.oopdemo.dto.Identity;
import com.netcompany.oopdemo.dto.Register;
import com.netcompany.oopdemo.service.ActivityService;
import com.netcompany.oopdemo.service.UserService;

public class RegisterActivityMenuItem implements MenuItem {
    private final ActivityService activityService;
    private final ConsoleContext appCtx;
    private Activity activity;
    private Register registerRecord;

    public RegisterActivityMenuItem(ConsoleContext appCtx) {
        this.appCtx = appCtx;
        this.activityService = new ActivityService(appCtx.getConnection());
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Register getRegisterRecord() {
        return registerRecord;
    }

    public void setRegisterRecord(Register registerRecord) {
        this.registerRecord = registerRecord;
    }

    private void registerActivity(boolean isRegister) {
        this.registerRecord = this.activityService.registerActivity(
                this.appCtx.getIdentity().getUserId(), this.activity.getId(), isRegister
        );
    }

    @Override
    public String getItemName() {
        String action = "Sign up";
        if (registerRecord != null) {
            action = "Cancel";
        }
        return action + " this activity";
    }

    @Override
    public void launch() {
        if (registerRecord == null) {
            registerActivity(true);
            System.out.println("You successfully signed up for this activity. Press enter to continues!");
        } else {
            registerActivity(false);
            System.out.println("You successfully cancelled for this activity. Press enter to continues!");
        }
        appCtx.getConsole().readLine();
    }
}