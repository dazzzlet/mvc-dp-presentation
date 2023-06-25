package com.netcompany.demo.view;

import com.netcompany.demo.core.EventArg;
import com.netcompany.demo.dto.Activity;
import com.netcompany.demo.model.ActivityModel;
import com.netcompany.demo.mvc.AbstractMenuView;
import com.netcompany.demo.utils.StringUtils;

import java.io.Console;

public class MemberViewActivityView extends AbstractMenuView<ActivityModel> {
    public MemberViewActivityView(Console console) {
        super(console);
    }

    @Override
    public EventArg updateModel(ActivityModel model) {
        Activity activity = model.getActivity();
        String date = StringUtils.formatDate(activity.getDateValue(), "dd MMM, YYYY");
        int userId = model.getIdentity().getUserId();
        String registerOn = model.getSignedUpList()
                .stream()
                .filter(su -> su.getUserId() == userId)
                .findFirst()
                .map(su -> String.format("Signed up on: %s\n",
                        StringUtils.formatDate(su.getRegisterOnValue(), "dd MMM, YYYY")
                ))
                .orElse("");
        model.setHeader(
                String.format(
                        "Activity detail\n" +
                                "      ---\n\n" +
                                "Title: %s\n" +
                                "Description: %s\n" +
                                "Date: %s\n%s",
                        activity.getTitle(),
                        activity.getDescription(),
                        date,
                        registerOn
                )
        );
        return super.updateModel(model);
    }
}
