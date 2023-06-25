package com.netcompany.demo.core;

import java.util.List;

public interface Menu {
    List<MenuItem> getMenuItems();

    String getMenuHeader();

    String getBackItemName();

    void launch();
}
