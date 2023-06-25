package com.netcompany.demo.view;

import com.netcompany.demo.model.ActivityListModel;
import com.netcompany.demo.mvc.AbstractMenuView;

import java.io.Console;

public class ActivityListView extends AbstractMenuView<ActivityListModel> {
    public ActivityListView(Console console) {
        super(console);
    }
}
