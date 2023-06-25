package com.netcompany.demo.event;

import com.netcompany.demo.core.EventArg;

public class ActivityUpdateEventArg extends EventArg {
    public String newTitle;
    public String newDescription;
    public String newDate;

    public ActivityUpdateEventArg(String newTitle, String newDescription, String newDate) {
        this.newTitle = newTitle;
        this.newDescription = newDescription;
        this.newDate = newDate;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public String getNewDate() {
        return newDate;
    }

    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }
}
