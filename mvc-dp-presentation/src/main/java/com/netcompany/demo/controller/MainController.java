package com.netcompany.demo.controller;

import com.netcompany.demo.core.*;

public class MainController extends AbstractController<Model> {
    public MainController(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    protected Action launch(View sender, EventArg arg) {
        if (this.getApplicationContext().isTerminatingApplication()) {
            return EmptyAction.getEmptyAction();
        } else if (this.getApplicationContext().getIdentity() == null) {
            return new DispatchAction(
                    LoginController.class
            );
        } else {
            return new DispatchAction(
                    DashboardController.class
            );
        }
    }
}
