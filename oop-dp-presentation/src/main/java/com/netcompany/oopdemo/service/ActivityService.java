package com.netcompany.oopdemo.service;

import java.sql.Connection;
import java.time.Instant;
import java.util.List;

import com.netcompany.oopdemo.dto.Activity;
import com.netcompany.oopdemo.dto.Identity;
import com.netcompany.oopdemo.dto.Register;
import com.netcompany.oopdemo.repository.ActivityRepository;
import com.netcompany.oopdemo.repository.RegisterRepository;

public class ActivityService {
    private final ActivityRepository activityRepository;
    private final RegisterRepository registerRepository;

    public ActivityService(Connection conn) {
        this.registerRepository = new RegisterRepository(conn);
        this.activityRepository = new ActivityRepository(conn);
    }

    public List<Activity> getAllActivity() {
        return this.activityRepository.getAllActivity();
    }

    public List<Register> getAllRegisteredUserForActivity(int userId) {
        return this.registerRepository.getAllRegisteredUserForActivity(userId);
    }

    public List<Register> getAllRegisteredActivityForUser(int userId) {
        return this.registerRepository.getAllRegisteredActivityForUser(userId);
    }

    public Register getRegisteredUserForActivity(int userId, int activityId) {
        return this.registerRepository.getRegisteredUserForActivity(userId, activityId);
    }

    public Register registerActivity(int userId, int activityId, boolean isRegister) {
        if (isRegister) {
            Register register = new Register(userId, activityId, Instant.now().toEpochMilli());
            this.registerRepository.registerActivity(register);
            return register;
        } else {
            this.registerRepository.unregisterActivity(userId, activityId);
            return null;
        }
    }

    public void updateActivity(Activity activity, Identity updateBy) {
        activity.setUpdatedBy(updateBy.getUserId());
        activity.setUpdatedOn(Instant.now().toEpochMilli());
        this.activityRepository.update(activity);
    }
}
