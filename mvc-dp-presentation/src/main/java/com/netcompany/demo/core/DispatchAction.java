package com.netcompany.demo.core;

import java.util.Map;

public class DispatchAction implements Action {
    private final Class<?> controllerClass;
    private final Map<String, Object> navigationParam;

    public DispatchAction(Class<?> controllerClass, Map<String, Object> navigationParam) {
        this.controllerClass = controllerClass;
        this.navigationParam = navigationParam;
    }

    public DispatchAction(Class<?> controllerClass) {
        this(controllerClass, null);
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Map<String, Object> getNavigationParam() {
        return navigationParam;
    }
}
