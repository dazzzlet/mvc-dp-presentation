package com.netcompany.demo.controller;

import com.netcompany.demo.core.*;
import com.netcompany.demo.dto.Identity;
import com.netcompany.demo.dto.User;
import com.netcompany.demo.event.LoginEventArg;
import com.netcompany.demo.model.LoginModel;
import com.netcompany.demo.view.LoginView;
import com.netcompany.demo.service.UserService;

public class LoginController extends AbstractController<LoginModel> {
    private final UserService userService;
    private final LoginView loginView;

    public LoginController(ApplicationContext applicationContext) {
        super(applicationContext);
        this.userService = new UserService(applicationContext.getConnection());
        this.setModel(new LoginModel());
        this.loginView = new LoginView(this.getApplicationContext().getConsole());
    }

    @Override
    protected Action launch(View sender, EventArg arg) {
        System.out.println("Login:" + sender + "| Arg:" + arg);
        if (sender == null) {
            return new ViewAction(loginView);
        } else {
            if (arg instanceof LoginEventArg) {
                LoginEventArg loginArg = (LoginEventArg) arg;
                return this.handleLoginEvent(loginArg);
            }
            return EmptyAction.getEmptyAction();
        }
    }

    private Action handleLoginEvent(LoginEventArg loginArg) {
        if (this.loginUser(loginArg.getUserName(), loginArg.getPassword())) {
            return EmptyAction.getEmptyAction();
        } else {
            this.getModel().setSuccess(false);
            this.getModel().setMessage("Username or password is invalid. Please tried again!");
            return new ViewAction(loginView);
        }
    }

    private boolean loginUser(String userName, String password) {
        User user = this.userService.getUserByUsernamePassword(userName, password);
        if (user != null) {
            Identity identity = new Identity(user);
            this.getApplicationContext().setIdentity(identity);
            return true;
        }
        return false;
    }
}
