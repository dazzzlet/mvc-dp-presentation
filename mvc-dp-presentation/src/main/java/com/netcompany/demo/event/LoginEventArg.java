package com.netcompany.demo.event;

import com.netcompany.demo.core.EventArg;

public class LoginEventArg extends EventArg {
    private String userName;
    private String password;

    public LoginEventArg(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
