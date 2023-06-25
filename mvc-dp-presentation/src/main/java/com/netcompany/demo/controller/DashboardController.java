package com.netcompany.demo.controller;

import com.netcompany.demo.core.*;
import com.netcompany.demo.dto.Identity;
import com.netcompany.demo.enums.UserRole;
import com.netcompany.demo.event.GeneralMenuEventArg;
import com.netcompany.demo.model.DashboardModel;
import com.netcompany.demo.mvc.AbstractAuthorizedController;
import com.netcompany.demo.view.DashboardView;

import java.util.ArrayList;
import java.util.List;

import static com.netcompany.demo.utils.Constants.APPLICATION_HEADER;

public class DashboardController extends AbstractAuthorizedController<DashboardModel> {
    private final List<MenuItem> organizerDashboard;
    private final List<MenuItem> memberDashboard;
    private final DashboardView view;

    public DashboardController(ApplicationContext applicationContext) {
        super(applicationContext);
        this.organizerDashboard = this.initiateOrganizerMenuItems();
        this.memberDashboard = this.initiateMemberMenuItems();
        this.view = new DashboardView(applicationContext.getConsole());
        this.setModel(new DashboardModel());
        this.getModel().setExitItem(ExitItem.getExitItem("Exit"));
    }

    private List<MenuItem> initiateMemberMenuItems() {
        List<MenuItem> result = new ArrayList<>();
        result.add(DispatcherItem.getDispatcher(
                "Club's activities",
                ActivityListController.class
        ));
        result.add(DispatcherItem.getDispatcher(
                "View my profile",
                UserController.class
        ));
        return result;
    }

    private List<MenuItem> initiateOrganizerMenuItems() {
        List<MenuItem> result = new ArrayList<>();
        result.add(DispatcherItem.getDispatcher(
                "List of activity",
                ActivityListController.class
        ));
        result.add(DispatcherItem.getDispatcher(
                "List of user",
                UserListController.class
        ));
        return result;
    }

    @Override
    protected Action launch(View sender, EventArg arg) {
        if (sender != null) {
            if (arg instanceof GeneralMenuEventArg) {
                GeneralMenuEventArg dashboardArg = (GeneralMenuEventArg) arg;
                if (dashboardArg.getSelectedMenuItem() instanceof DispatcherItem) {
                    DispatcherItem dispatcherItem = (DispatcherItem) dashboardArg.getSelectedMenuItem();
                    return new DispatchAction(
                            dispatcherItem.getControllerClass()
                            , dispatcherItem.getNavigationParams()
                    );
                }
            }
        } else {
            Identity identity = this.getIdentity();
            if (identity != null) {
                this.manipulateModel(identity);
                return new ViewAction(this.view);
            }
        }
        this.getApplicationContext().setTerminatingApplication(true);
        return EmptyAction.getEmptyAction();
    }

    private void manipulateModel(Identity identity) {
        if (identity.getRole() == UserRole.Member) {
            this.getModel().setHeader(
                    String.format("%s\n\t\tWelcome %s\nMember dashboard", APPLICATION_HEADER, identity.getFullName())
            );
            this.getModel().setMenuItems(this.memberDashboard);
        } else {
            this.getModel().setHeader(
                    String.format("%s\n\t\tWelcome %s\nOrganizer dashboard", APPLICATION_HEADER, identity.getFullName())
            );
            this.getModel().setMenuItems(this.organizerDashboard);
        }
    }
}
