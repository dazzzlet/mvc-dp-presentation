package com.netcompany.demo.view;

import com.netcompany.demo.model.UserListModel;
import com.netcompany.demo.mvc.AbstractMenuView;

import java.io.Console;

public class UserListView extends AbstractMenuView<UserListModel> {
    public UserListView(Console console) {
        super(console);
    }
}
