package com.netcompany.demo.model;

import com.netcompany.demo.dto.Register;
import com.netcompany.demo.dto.User;
import com.netcompany.demo.mvc.AbstractMenuModel;

import java.util.List;

public class UserModel extends AbstractMenuModel {
    private User user;
    private Boolean isSuccess;
    private String message;
    private List<Register> registerRecords;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Register> getRegisterRecords() {
        return registerRecords;
    }

    public void setRegisterRecords(List<Register> registerRecords) {
        this.registerRecords = registerRecords;
    }
}
