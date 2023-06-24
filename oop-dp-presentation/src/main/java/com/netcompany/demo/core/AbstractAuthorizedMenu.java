package com.netcompany.demo.core;

import com.netcompany.demo.dto.Identity;
import com.netcompany.demo.enums.UserRole;

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
