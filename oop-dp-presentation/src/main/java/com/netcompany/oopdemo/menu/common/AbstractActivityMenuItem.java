package com.netcompany.oopdemo.menu.common;

import com.netcompany.oopdemo.core.ConsoleContext;
import com.netcompany.oopdemo.core.MenuItem;
import com.netcompany.oopdemo.dto.Activity;

public abstract class AbstractActivityMenuItem implements MenuItem {
    protected final ConsoleContext appCtx;
    private  Activity activity;

    public AbstractActivityMenuItem(ConsoleContext appCtx) {
        this.appCtx = appCtx;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }
}
