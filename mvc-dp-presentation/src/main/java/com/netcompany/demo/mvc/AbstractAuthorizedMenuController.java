package com.netcompany.demo.mvc;

import com.netcompany.demo.core.*;
import com.netcompany.demo.event.GeneralMenuEventArg;
import com.netcompany.demo.model.MenuModel;

public abstract class AbstractAuthorizedMenuController<TModel extends MenuModel> extends AbstractAuthorizedController<TModel> {
    private AbstractView<TModel> view;

    public AbstractAuthorizedMenuController(
            ApplicationContext applicationContext
            , AbstractView<TModel> view
            , TModel newInstance
            , String exitItemTitle
    ) {
        super(applicationContext);
        this.view = view;
        this.setModel(newInstance);
        if (exitItemTitle != null) {
            this.getModel().setExitItem(ExitItem.getExitItem(exitItemTitle));
        }
    }

    public AbstractAuthorizedMenuController(
            ApplicationContext applicationContext
            , AbstractMenuView<TModel> view
            , TModel newInstance
    ) {
        this(applicationContext, view, newInstance, "Go back");
    }

    protected AbstractView<TModel> getView() {
        return view;
    }

    protected void setView(AbstractView<TModel> view) {
        this.view = view;
    }

    @Override
    protected Action launch(View sender, EventArg arg) {
        if (sender == null) {
            if (this.initiateModel()) {
                return new ViewAction(this.getView());
            }
        } else {
            if (arg instanceof GeneralMenuEventArg) {
                GeneralMenuEventArg menuArg = (GeneralMenuEventArg) arg;
                return this.handleGeneralMenuEvent(sender, menuArg);
            } else {
                return this.handleOtherEvent(sender, arg);
            }
        }
        return EmptyAction.getEmptyAction();
    }

    protected Action handleOtherEvent(View sender, EventArg arg) {
        return EmptyAction.getEmptyAction();
    }

    protected Action handleGeneralMenuEvent(View sender, GeneralMenuEventArg menuArg) {
        if (menuArg.getSelectedMenuItem() instanceof DispatcherItem) {
            DispatcherItem dispatcherItem = (DispatcherItem) menuArg.getSelectedMenuItem();
            return new DispatchAction(
                    dispatcherItem.getControllerClass()
                    , dispatcherItem.getNavigationParams()
            );
        }
        return EmptyAction.getEmptyAction();
    }

    protected abstract boolean initiateModel();
}
