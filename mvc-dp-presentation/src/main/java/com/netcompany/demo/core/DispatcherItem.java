package com.netcompany.demo.core;

import java.util.Map;

public class DispatcherItem implements MenuItem {
    private final String itemName;
    private final Class<?> controllerClass;
    private final Map<String, Object> navigationParams;

    private DispatcherItem(
            String itemName
            , Class<?> controllerClass
            , Map<String, Object> navigationParams
    ) {
        this.itemName = itemName;
        this.controllerClass = controllerClass;
        this.navigationParams = navigationParams;
    }

    public static <T extends AbstractController<?>> DispatcherItem getDispatcher(
            String itemName
            , Class<T> controllerClass
            , Map<String, Object> navigationParams
    ) {
        return new DispatcherItem(itemName, controllerClass, navigationParams);
    }

    public static <T extends AbstractController<?>> DispatcherItem getDispatcher(
            String itemName
            , Class<T> controllerClass
    ) {
        return getDispatcher(itemName, controllerClass, null);
    }

    @Override
    public String getItemName() {
        return this.itemName;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Map<String, Object> getNavigationParams() {
        return navigationParams;
    }
}
