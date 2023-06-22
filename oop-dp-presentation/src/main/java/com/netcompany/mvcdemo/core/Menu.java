package com.netcompany.mvcdemo.core;

import java.util.List;

public interface Menu {
    List<MenuItem> getMenuItems();

    String getMenuHeader();

    String getBackItemName();

    void launch();
}
