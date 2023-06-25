package com.netcompany.demo.view;

import com.netcompany.demo.model.DashboardModel;
import com.netcompany.demo.mvc.AbstractMenuView;

import java.io.Console;

public class DashboardView extends AbstractMenuView<DashboardModel> {
    public DashboardView(Console console) {
        super(console);
    }
}
