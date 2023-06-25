package com.netcompany.demo.controller;

import com.netcompany.demo.core.*;
import com.netcompany.demo.dto.Activity;
import com.netcompany.demo.dto.Identity;
import com.netcompany.demo.enums.ActivityStatus;
import com.netcompany.demo.enums.UserRole;
import com.netcompany.demo.model.ActivityListModel;
import com.netcompany.demo.mvc.AbstractAuthorizedMenuController;
import com.netcompany.demo.service.ActivityService;
import com.netcompany.demo.utils.Constants;
import com.netcompany.demo.utils.StringUtils;
import com.netcompany.demo.view.ActivityListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityListController extends AbstractAuthorizedMenuController<ActivityListModel> {
    private final ActivityService activityService;

    public ActivityListController(ApplicationContext applicationContext) {
        super(
                applicationContext
                , new ActivityListView(applicationContext.getConsole())
                , new ActivityListModel()
                , "Back to dashboard"
        );
        this.activityService = new ActivityService(this.getApplicationContext().getConnection());
    }

    @Override
    protected boolean initiateModel() {
        Identity identity = this.getIdentity();
        List<Activity> activityList = this.activityService.getAllActivity();
        if (identity.getRole() == UserRole.Member) {
            activityList = activityList
                    .stream()
                    .filter(a -> a.getStatus() == ActivityStatus.Published)
                    .collect(Collectors.toList());
            this.getModel().setHeader("Club's activities");
        } else {
            this.getModel().setHeader("List of activity");
        }
        List<MenuItem> menuItems = new ArrayList<>();
        for (Activity activity : activityList) {
            menuItems.add(DispatcherItem.getDispatcher(
                    String.format(
                            "[%s] %s"
                            , StringUtils.formatDate(activity.getDateValue(), "dd MMM YYYY")
                            , activity.getTitle()
                    ),
                    ActivityController.class,
                    Collections.singletonMap(Constants.ACTIVITY_ITEM, activity)
            ));
        }
        this.getModel().setMenuItems(menuItems);
        return true;
    }
}
