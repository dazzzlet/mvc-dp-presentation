package com.netcompany.demo.event;

import com.netcompany.demo.core.EventArg;
import com.netcompany.demo.core.MenuItem;

public class GeneralMenuEventArg extends EventArg {
    private final MenuItem selectedMenuItem;

    public GeneralMenuEventArg(MenuItem selectedMenuItem) {
        this.selectedMenuItem = selectedMenuItem;
    }

    public MenuItem getSelectedMenuItem() {
        return selectedMenuItem;
    }
}
