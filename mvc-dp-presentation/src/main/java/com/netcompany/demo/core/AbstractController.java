package com.netcompany.demo.core;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractController<TModel extends Model> {
    private final ApplicationContext applicationContext;
    private final Map<String, Object> navigationParameters;
    private TModel model = null;

    public AbstractController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.navigationParameters = new HashMap<>();
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public TModel getModel() {
        return model;
    }

    public void setModel(TModel model) {
        this.model = model;
    }

    protected abstract Action launch(View sender, EventArg arg);

    private void launch() {
        Action action = null;
        View lastViewCall = null;
        EventArg viewEventArg = null;
        do {
            action = this.launch(lastViewCall, viewEventArg);
            if (action != null) {
                if (action instanceof ViewAction) {
                    ViewAction viewAction = (ViewAction) action;
                    AbstractView<TModel> view = (AbstractView<TModel>) viewAction.getView();
                    viewEventArg = view.updateModel(this.getModel());
                    lastViewCall = view;
                } else if (action instanceof DispatchAction) {
                    DispatchAction dispatchAction = (DispatchAction) action;
                    AbstractController<?> controller = this.applicationContext.getControllerMap()
                            .get(dispatchAction.getControllerClass());
                    controller.launchFromOtherController(dispatchAction.getNavigationParam());
                    lastViewCall = null;
                    viewEventArg = null;
                }
            }
        } while (action != null && !(action instanceof EmptyAction));
    }

    public static void launch(AbstractController<?> controller) {
        controller.launch();
    }

    public <TParam> TParam getNavigationParam(String key) {
        if (this.navigationParameters.containsKey(key)) {
            return (TParam) this.navigationParameters.get(key);
        }
        return null;
    }

    private void launchFromOtherController(Map<String, Object> parameters) {
        this.navigationParameters.clear();
        if (parameters != null) {
            this.navigationParameters.putAll(parameters);
        }
        this.launch();
    }
}
