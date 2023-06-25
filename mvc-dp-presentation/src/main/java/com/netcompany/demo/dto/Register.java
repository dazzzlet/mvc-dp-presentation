package com.netcompany.demo.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Register implements DTO {
    private int userId;
    private int activityId;
    private long registerOn;

    public Register(int userId, int activityId, long registerOn) {
        this.userId = userId;
        this.activityId = activityId;
        this.registerOn = registerOn;
    }

    public Register() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public long getRegisterOn() {
        return registerOn;
    }

    public void setRegisterOn(long registerOn) {
        this.registerOn = registerOn;
    }

    @Override
    public void loadDataRow(ResultSet rs) throws SQLException {
        this.userId = rs.getInt(1);
        this.activityId = rs.getInt(2);
        this.registerOn = rs.getLong(3);
    }

    public Date getRegisterOnValue() {
        return new Date(this.registerOn);
    }
}
