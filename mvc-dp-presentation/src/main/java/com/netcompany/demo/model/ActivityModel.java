package com.netcompany.demo.model;

import com.netcompany.demo.dto.Activity;
import com.netcompany.demo.dto.Identity;
import com.netcompany.demo.dto.Register;
import com.netcompany.demo.dto.User;
import com.netcompany.demo.mvc.AbstractMenuModel;

import java.util.List;

public class ActivityModel extends AbstractMenuModel {
    private Activity activity;
    private List<Register> signedUpList;
    private Identity identity;
    private User createdByUser;
    private User updatedByUser;
    private Boolean isSuccess;
    private String message;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public List<Register> getSignedUpList() {
        return signedUpList;
    }

    public void setSignedUpList(List<Register> signedUpList) {
        this.signedUpList = signedUpList;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public User getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(User updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    public Boolean getSuccess() {
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
}
