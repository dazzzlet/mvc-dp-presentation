package com.netcompany.demo.controller;

import com.netcompany.demo.core.*;
import com.netcompany.demo.dto.Identity;
import com.netcompany.demo.dto.User;
import com.netcompany.demo.enums.UserRole;
import com.netcompany.demo.model.UserListModel;
import com.netcompany.demo.mvc.AbstractAuthorizedMenuController;
import com.netcompany.demo.service.UserService;
import com.netcompany.demo.utils.Constants;
import com.netcompany.demo.view.UserListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserListController extends AbstractAuthorizedMenuController<UserListModel> {
    private final UserService userService;

    public UserListController(ApplicationContext applicationContext) {
        super(
                applicationContext
                , new UserListView(applicationContext.getConsole())
                , new UserListModel()
                , "Back to dashboard"
        );
        this.userService = new UserService(this.getApplicationContext().getConnection());
    }

    @Override
    protected boolean initiateModel() {
        Identity identity = this.getIdentity();
        List<User> users = this.userService.getAllUsers();
        if (identity.getRole() == UserRole.Member) {
            return false;
        } else {
            this.getModel().setHeader("List of user");
        }
        List<MenuItem> menuItems = new ArrayList<>();
        for (User user : users) {
            menuItems.add(DispatcherItem.getDispatcher(
                    String.format(
                            "%d | [%s] %s"
                            , user.getId()
                            , user.getRole().name()
                            , user.getUsername()
                    ),
                    UserController.class,
                    Collections.singletonMap(Constants.USER_ITEM, user)
            ));
        }
        this.getModel().setMenuItems(menuItems);
        return true;
    }
}
