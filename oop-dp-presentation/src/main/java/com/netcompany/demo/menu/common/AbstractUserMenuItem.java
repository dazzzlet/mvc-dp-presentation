package com.netcompany.demo.menu.common;

import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.core.MenuItem;
import com.netcompany.demo.dto.Activity;
import com.netcompany.demo.dto.User;

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
