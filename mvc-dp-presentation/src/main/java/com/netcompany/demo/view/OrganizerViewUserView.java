package com.netcompany.demo.view;

import com.netcompany.demo.core.EventArg;
import com.netcompany.demo.model.UserModel;
import com.netcompany.demo.mvc.AbstractMenuView;

import java.io.Console;

public class OrganizerViewUserView extends AbstractMenuView<UserModel> {
    public OrganizerViewUserView(Console console) {
        super(console);
    }

    @Override
    public EventArg updateModel(UserModel model) {
        model.setHeader(String.format(
                "User detail\n" +
                        "      ---\n\n" +
                        "ID: %d\n" +
                        "Username: %s\n" +
                        "First name: %s\n" +
                        "Bio: %s\n" +
                        "Role: %s\n" +
                        "Number of signed up activities: %d\n",
                model.getUser().getId(),
                model.getUser().getUsername(),
                model.getUser().getFirstname(),
                model.getUser().getBio(),
                model.getUser().getRole().name(),
                model.getRegisterRecords().size()
        ));
        return super.updateModel(model);
    }
}
