package com.netcompany.demo.view;

import com.netcompany.demo.core.EventArg;
import com.netcompany.demo.model.UserModel;
import com.netcompany.demo.mvc.AbstractMenuView;

import java.io.Console;

public class ViewUserProfileView extends AbstractMenuView<UserModel> {
    public ViewUserProfileView(Console console) {
        super(console);
    }

    @Override
    public EventArg updateModel(UserModel model) {
        model.setHeader(String.format(
                "My profile\n" +
                        "      ---\n\n" +
                        "Username: %s\n" +
                        "First name: %s\n" +
                        "Bio: %s\n" +
                        "Number of signed up activities: %d\n",
                model.getUser().getUsername(),
                model.getUser().getFirstname(),
                model.getUser().getBio(),
                model.getRegisterRecords().size()
        ));
        return super.updateModel(model);
    }
}
