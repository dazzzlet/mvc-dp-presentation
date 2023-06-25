package com.netcompany.demo.core;

public class DefinedItem implements MenuItem{
    private final String itemName;
    private final String action;

    public DefinedItem(String itemName, String action) {
        this.itemName = itemName;
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String getItemName() {
        return this.itemName;
    }
}
