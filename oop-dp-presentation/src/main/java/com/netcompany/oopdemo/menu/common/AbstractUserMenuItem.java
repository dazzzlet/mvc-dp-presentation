package com.netcompany.oopdemo.menu.common;

import com.netcompany.oopdemo.core.ConsoleContext;
import com.netcompany.oopdemo.core.MenuItem;
import com.netcompany.oopdemo.dto.Activity;
import com.netcompany.oopdemo.dto.User;

public abstract class AbstractUserMenuItem implements MenuItem {
    protected final ConsoleContext appCtx;
    private User user;

    public AbstractUserMenuItem(ConsoleContext appCtx) {
        this.appCtx = appCtx;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
