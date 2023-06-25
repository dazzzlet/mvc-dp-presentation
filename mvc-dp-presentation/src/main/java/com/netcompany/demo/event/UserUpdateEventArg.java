package com.netcompany.demo.event;

import com.netcompany.demo.core.EventArg;

public class UserUpdateEventArg extends EventArg {
    private String newFirstName;
    private String newBio;

    public UserUpdateEventArg(String newBio) {
        this("", newBio);
    }

    public UserUpdateEventArg(String newFirstName, String newBio) {
        this.newFirstName = newFirstName;
        this.newBio = newBio;
    }

    public String getNewFirstName() {
        return newFirstName;
    }

    public void setNewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
    }

    public String getNewBio() {
        return newBio;
    }

    public void setNewBio(String newBio) {
        this.newBio = newBio;
    }
}
