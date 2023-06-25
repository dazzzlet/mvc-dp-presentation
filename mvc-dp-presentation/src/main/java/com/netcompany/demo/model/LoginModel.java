package com.netcompany.demo.model;

import com.netcompany.demo.core.Model;

public class LoginModel implements Model {
    private String message;
    private Boolean isSuccess;

    public LoginModel() {
        this(null, null);
    }

    public LoginModel(String message, Boolean isSuccess) {
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }
}
