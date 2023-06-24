package com.netcompany.mvcdemo.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class Event implements DTO {
    private int id;
    private String name;
    private Time startTime;
    private Time endTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

   public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public void loadDataRow(ResultSet rs) throws SQLException {
        this.id = rs.getInt(1);
        this.name = rs.getString(2);
        this.startTime = rs.getTime(3);
        this.endTime = rs.getTime(4);
    }
}
