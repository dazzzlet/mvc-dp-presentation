package com.netcompany.demo.view;

import com.netcompany.demo.core.AbstractView;
import com.netcompany.demo.core.EventArg;
import com.netcompany.demo.dto.User;
import com.netcompany.demo.event.ActivityUpdateEventArg;
import com.netcompany.demo.event.UserUpdateEventArg;
import com.netcompany.demo.model.UserModel;
import com.netcompany.demo.utils.StringUtils;
import com.netcompany.demo.utils.ConsoleUtils;

import java.io.Console;

public class OrganizerUpdateUserView extends AbstractView<UserModel> {
    private final Console console;

    public OrganizerUpdateUserView(Console console) {
        this.console = console;
    }

    @Override
    public EventArg updateModel(UserModel model) {
        if (model.isSuccess() != null) {
            this.renderMessage(model.getMessage());
            if (model.isSuccess()) {
                return null;
            }
        } else {
            System.out.println("Update user - " + model.getUser().getUsername());
            System.out.println("      ---");
            System.out.println();
        }
        return this.renderForm(model.getUser());
    }

    private EventArg renderForm(User user) {
        System.out.print(
                String.format("Full name (%s): ", user.getFirstname())
        );
        String newFirstName = console.readLine();
        System.out.print(
                String.format("Bio (%s): ", user.getBio())
        );
        String newBio = console.readLine();
        UserUpdateEventArg updateArg = new UserUpdateEventArg(newFirstName, newBio);
        return updateArg;
    }

    private void renderMessage(String message) {
        ConsoleUtils.cleanConsole();
        System.out.println(message);
        console.readLine();
    }

}
