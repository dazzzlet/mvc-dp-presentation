package com.netcompany.demo.core;

public class ExitItem implements MenuItem {
    private final String exitTitle;

    private ExitItem(String exitTitle) {
        this.exitTitle = exitTitle;
    }

    public static MenuItem getExitItem(String exitTitle){
        return  new ExitItem(exitTitle);
    }

    @Override
    public String getItemName() {
        return this.exitTitle;
    }
}
