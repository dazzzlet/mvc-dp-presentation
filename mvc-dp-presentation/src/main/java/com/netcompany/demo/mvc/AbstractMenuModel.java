package com.netcompany.demo.mvc;

import com.netcompany.demo.core.MenuItem;
import com.netcompany.demo.model.MenuModel;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMenuModel implements MenuModel {
    protected List<MenuItem> menuItems;
    protected String header;
    protected MenuItem exitItem;

    public AbstractMenuModel(){
        menuItems = new ArrayList<>();
    }

    @Override
    public String getSelectInstruction() {
        return "Enter your choise: ";
    }

    @Override
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public MenuItem getExitItem() {
        return exitItem;
    }

    public void setExitItem(MenuItem exitItem) {
        this.exitItem = exitItem;
    }
}
