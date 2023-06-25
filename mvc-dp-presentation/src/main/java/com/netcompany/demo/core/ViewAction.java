package com.netcompany.demo.core;

public class ViewAction implements Action{
    private final AbstractView<?> view;

    public ViewAction(AbstractView<?> view) {
        this.view = view;
    }

    public AbstractView<?> getView() {
        return view;
    }
}
