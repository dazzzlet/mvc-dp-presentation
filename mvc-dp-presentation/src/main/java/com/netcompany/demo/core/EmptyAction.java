package com.netcompany.demo.core;

public class EmptyAction implements Action {
    private EmptyAction() {
    }

    public static Action getEmptyAction() {
        return new EmptyAction();
    }
}
