package com.netcompany.demo.view;

import com.netcompany.demo.core.AbstractView;
import com.netcompany.demo.core.EventArg;
import com.netcompany.demo.event.LoginEventArg;
import com.netcompany.demo.model.LoginModel;
import com.netcompany.demo.utils.ConsoleUtils;

import java.io.Console;

import static com.netcompany.demo.utils.Constants.APPLICATION_HEADER;

public class LoginView extends AbstractView<LoginModel> {
    private final Console console;

    public LoginView(Console console) {
        this.console = console;
    }

    @Override
    public EventArg updateModel(LoginModel model) {
        if (model.getSuccess() != null && model.getSuccess()) {
            return null;
        }
        if (model.getSuccess() == null) {
            System.out.println(APPLICATION_HEADER);
            System.out.println("           LOGIN");
            System.out.println();
        } else {
            this.renderMessage(model.getMessage());
        }
        return this.renderForm();
    }

    private void renderMessage(String message) {
        ConsoleUtils.cleanConsole();
        System.out.println(message);
        console.readLine();
    }

    private EventArg renderForm() {
        System.out.print("Username: ");
        String userName = console.readLine();
        String password = String.valueOf(console.readPassword("Password: "));
        return new LoginEventArg(userName, password);
    }
}
