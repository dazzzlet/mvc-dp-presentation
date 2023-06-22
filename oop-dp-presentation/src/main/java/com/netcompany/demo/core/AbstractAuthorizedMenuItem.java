package com.netcompany.demo.core;

import com.netcompany.demo.dto.Identity;
import com.netcompany.demo.enums.UserRole;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAuthorizedMenuItem implements MenuItem {
    protected final Identity identity;
    protected ConsoleContext appCtx;

    public AbstractAuthorizedMenuItem(ConsoleContext appCtx) {
        this.appCtx = appCtx;
        this.identity = this.appCtx.getIdentity();
    }

    public Identity getIdentity() {
        return identity;
    }

    public UserRole getUserRole() {
        return this.identity.getRole();
    }

    public String getUserFullName() {
        return this.identity.getFullName();
    }

    @Override
    public String getItemName() {
        return null;
    }

    @Override
    public void launch() {

    }
}
