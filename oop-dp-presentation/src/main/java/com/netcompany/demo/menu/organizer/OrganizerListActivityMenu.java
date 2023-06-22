package com.netcompany.demo.menu.organizer;

import com.netcompany.demo.core.AbstractAuthorizedCollectionMenu;
import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.core.MenuItem;
import com.netcompany.demo.dto.Activity;
import com.netcompany.demo.service.ActivityService;
import com.netcompany.demo.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrganizerListActivityMenu extends AbstractAuthorizedCollectionMenu implements MenuItem {
    private final ArrayList<MenuItem> activityList;
    private final ActivityService activityService;
    private final OrganizerViewActivityMenu viewActivityMenu;

    public OrganizerListActivityMenu(ConsoleContext appCtx) {
        super(appCtx);
        this.viewActivityMenu = new OrganizerViewActivityMenu(appCtx);
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
        List<Activity> activities = this.activityService.getAllActivity();
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
            activityItem.setActivity(this.viewActivityMenu.getActivity());
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
        return "Manage activities";
    }

    private class ActivityItem implements MenuItem {
        private Activity activity;

        private ActivityItem(Activity activity) {
            this.activity = activity;
        }

        public Activity getActivity() {
            return activity;
        }

        public void setActivity(Activity activity) {
            this.activity = activity;
        }

        @Override
        public String getItemName() {
            return String.format(
                    "%s | %s"
                    , StringUtils.formatDate(this.activity.getDateValue(), "dd MMM")
                    , this.activity.getTitle()
            );
        }

        @Override
        public void launch() {

        }
    }
}
