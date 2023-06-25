package com.netcompany.demo.model;

import com.netcompany.demo.core.ExitItem;
import com.netcompany.demo.core.MenuItem;
import com.netcompany.demo.core.Model;

import java.util.List;

public interface MenuModel extends Model {
    List<MenuItem> getMenuItems();

    MenuItem getExitItem();

    void setExitItem(MenuItem exitItem);

    String getHeader();

    String getSelectInstruction();
}
