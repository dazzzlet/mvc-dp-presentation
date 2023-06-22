package com.netcompany.demo.menu.common;

import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.core.MenuItem;
import com.netcompany.demo.dto.Activity;

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
