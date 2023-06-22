package com.netcompany.demo.menu.member;

import com.netcompany.demo.core.AbstractAuthorizedCollectionMenu;
import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.core.MenuItem;
import com.netcompany.demo.dto.Activity;
import com.netcompany.demo.enums.ActivityStatus;
import com.netcompany.demo.menu.organizer.OrganizerViewActivityMenu;
import com.netcompany.demo.service.ActivityService;
import com.netcompany.demo.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MemberListActivityMenu extends AbstractAuthorizedCollectionMenu implements MenuItem {
    private final ArrayList<MenuItem> activityList;
    private final ActivityService activityService;
    private final MemberViewActivityMenu viewActivityMenu;

    public MemberListActivityMenu(ConsoleContext appCtx) {
        super(appCtx);
        this.viewActivityMenu = new MemberViewActivityMenu(appCtx);
        this.activityList = new ArrayList<>();
        this.activityService = new ActivityService(appCtx.getConnection());

    }

    @Override
    public void launch() {
        this.initializeData();
        super.launch();
    }

    private void initializeData() {
        this.activityList.clear();
        List<Activity> activities = this.activityService.getAllActivity()
                .stream()
                .filter(a -> a.getStatus() == ActivityStatus.Published)
                .collect(Collectors.toList());
        for (Activity activity : activities) {
            this.activityList.add(new ActivityItem(activity));
        }
    }

    @Override
    protected List<MenuItem> getCollection() {
        return this.activityList;
    }

    @Override
    protected List<MenuItem> getAdditionalMenuItem() {
        return Collections.emptyList();
    }

    @Override
    protected void launchCollectionItem(MenuItem menuItem) {
        if (menuItem instanceof ActivityItem) {
            ActivityItem activityItem = (ActivityItem) menuItem;
            this.viewActivityMenu.setActivity(activityItem.getActivity());
            this.viewActivityMenu.launch();
        }
    }

    @Override
    protected void launchAdditionalMenuItem(MenuItem menuItem) {

    }

    @Override
    public String getMenuHeader() {
        return "List of activity";
    }

    @Override
    public String getBackItemName() {
        return "Back to dashboard";
    }

    @Override
    public String getItemName() {
        return "Club's activities";
    }

    private class ActivityItem implements MenuItem {
        private final Activity activity;

        private ActivityItem(Activity activity) {
            this.activity = activity;
        }

        public Activity getActivity() {
            return activity;
        }

        @Override
        public String getItemName() {
            return String.format(
                    "[%s] %s"
                    , StringUtils.formatDate(this.activity.getDateValue(), "dd MMM YYYY")
                    , this.activity.getTitle()
            );
        }

        @Override
        public void launch() {

        }
    }
}
