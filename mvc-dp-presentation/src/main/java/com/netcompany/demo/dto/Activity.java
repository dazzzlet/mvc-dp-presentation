package com.netcompany.demo.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.netcompany.demo.enums.ActivityStatus;

public class Activity implements DTO {

    private int id;
    private String title;
    private String description;
    private long date;
    private long createdOn;
    private int createdBy;
    private Long updatedOn;
    private Integer updatedBy;
    private ActivityStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDate() {
        return date;
    }

    public Date getDateValue() {
        return new Date(this.date);
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public Date getCreatedOnValue() {
        return new Date(createdOn);
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedOn() {
        return updatedOn;
    }

    public Date getUpdatedOnValue() {
        if (updatedOn != null) {
            return new Date(updatedOn);
        }
        return null;
    }

    public void setUpdatedOn(Long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

    public Activity clone() {
        Activity newActivity = new Activity();
        newActivity.setId(this.id);
        newActivity.setTitle(this.title);
        newActivity.setDescription(this.description);
        newActivity.setDate(this.date);
        newActivity.setCreatedOn(this.createdOn);
        newActivity.setCreatedBy(this.createdBy);
        newActivity.setUpdatedOn(this.updatedOn);
        newActivity.setUpdatedBy(this.updatedBy);
        newActivity.setStatus(this.status);
        return newActivity;
    }

    @Override
    public void loadDataRow(ResultSet rs) throws SQLException {
        this.id = rs.getInt(1);
        this.title = rs.getString(2);
        this.description = rs.getString(3);
        this.date = rs.getLong(4);
        this.createdOn = rs.getLong(5);
        this.createdBy = rs.getInt(6);
        this.updatedOn = rs.getLong(7);
        if (rs.wasNull()) {
            this.updatedOn = null;
        }
        this.updatedBy = rs.getInt(8);
        if (rs.wasNull()) {
            this.updatedBy = null;
        }
        this.status = ActivityStatus.getFromInt(rs.getInt(1));
    }
}
