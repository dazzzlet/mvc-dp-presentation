package com.netcompany.oopdemo.core;

import java.util.List;

public interface Menu {
    List<MenuItem> getMenuItems();

    String getMenuHeader();

    String getBackItemName();

    void launch();
}
