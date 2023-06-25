package com.netcompany.demo.view;

import com.netcompany.demo.core.AbstractView;
import com.netcompany.demo.core.EventArg;
import com.netcompany.demo.dto.Activity;
import com.netcompany.demo.event.ActivityUpdateEventArg;
import com.netcompany.demo.model.ActivityModel;
import com.netcompany.demo.utils.ConsoleUtils;
import com.netcompany.demo.utils.StringUtils;

import java.io.Console;

public class OrganizerUpdateActivityView extends AbstractView<ActivityModel> {
    private final Console console;

    public OrganizerUpdateActivityView(Console console) {
        this.console = console;
    }

    @Override
    public EventArg updateModel(ActivityModel model) {
        if (model.getSuccess() != null) {
            this.renderMessage(model.getMessage());
            if (model.getSuccess()) {
                return null;
            }
        } else {
            System.out.println("Update activity");
            System.out.println("      ---");
            System.out.println();
        }
        return this.renderForm(model.getActivity());
    }

    private EventArg renderForm(Activity activity) {
        String newTitle = activity.getTitle();
        String newDescription = activity.getDescription();
        String newDate = StringUtils.formatDate(activity.getDateValue(), "yyyy/MM/dd");
        System.out.print(
                String.format("Title (%s): ", newTitle)
        );
        newTitle = console.readLine();
        System.out.print(
                String.format("Description (%s): ", newDescription)
        );
        newDescription = console.readLine();
        System.out.print(
                String.format("Date (%s): ", newDate)
        );
        newDate = console.readLine();
        ActivityUpdateEventArg updateArg = new ActivityUpdateEventArg(newTitle, newDescription, newDate);
        return updateArg;
    }

    private void renderMessage(String message) {
        ConsoleUtils.cleanConsole();
        System.out.println(message);
        console.readLine();
    }

}
