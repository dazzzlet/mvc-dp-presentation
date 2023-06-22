package com.netcompany.oopdemo.core;

import com.netcompany.oopdemo.dto.Identity;
import com.netcompany.oopdemo.enums.UserRole;

public abstract class AbstractAuthorizedMenu extends AbstractMenu {

    public AbstractAuthorizedMenu(ConsoleContext appCtx) {
        super(appCtx);
    }

    public Identity getIdentity() {
        return this.appCtx.getIdentity();
    }

    public UserRole getUserRole() {
        return this.getIdentity().getRole();
    }

    public String getUserFullName() {
        return this.getIdentity().getFullName();
    }
}
