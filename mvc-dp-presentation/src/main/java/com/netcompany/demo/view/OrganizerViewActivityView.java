package com.netcompany.demo.view;

import com.netcompany.demo.core.EventArg;
import com.netcompany.demo.dto.Activity;
import com.netcompany.demo.dto.User;
import com.netcompany.demo.model.ActivityModel;
import com.netcompany.demo.mvc.AbstractMenuView;
import com.netcompany.demo.utils.StringUtils;

import java.io.Console;

public class OrganizerViewActivityView extends AbstractMenuView<ActivityModel> {
    public OrganizerViewActivityView(Console console) {
        super(console);
    }

    @Override
    public EventArg updateModel(ActivityModel model) {
        Activity activity = model.getActivity();
        User createdByUser = model.getCreatedByUser();
        User updatedByUser = model.getUpdatedByUser();
        String createdByUserName = createdByUser.getFirstname();
        String createdOn = StringUtils.formatDate(activity.getCreatedOnValue(), "dd MMM, YYYY");
        String date = StringUtils.formatDate(activity.getDateValue(), "dd MMM, YYYY");
        String updatedByUserName = "n/a";
        String updatedOn = "n/a";
        if (updatedByUser != null) {
            updatedByUserName = updatedByUser.getFirstname();
            updatedOn = StringUtils.formatDate(activity.getUpdatedOnValue(), "dd MMM, YYYY");
        }
        model.setHeader(
                String.format(
                        "Activity detail\n" +
                                "      ---\n\n" +
                                "Title: %s\n" +
                                "Description: %s\n" +
                                "Date: %s\n" +
                                "Status: %s\n" +
                                "Number of signed up member: %d\n" +
                                "Created on: %s\n" +
                                "Created by: %s\n" +
                                "Updated on: %s\n" +
                                "Updated by: %s\n",
                        activity.getTitle(),
                        activity.getDescription(),
                        date,
                        activity.getStatus().name(),
                        model.getSignedUpList().size(),
                        createdOn,
                        createdByUserName,
                        updatedOn,
                        updatedByUserName
                )
        );
        return super.updateModel(model);
    }
}
