package com.netcompany.demo.controller;

import com.netcompany.demo.core.*;
import com.netcompany.demo.dto.Activity;
import com.netcompany.demo.dto.Register;
import com.netcompany.demo.dto.User;
import com.netcompany.demo.enums.UserRole;
import com.netcompany.demo.event.ActivityUpdateEventArg;
import com.netcompany.demo.event.GeneralMenuEventArg;
import com.netcompany.demo.model.ActivityModel;
import com.netcompany.demo.mvc.AbstractAuthorizedMenuController;
import com.netcompany.demo.service.ActivityService;
import com.netcompany.demo.service.UserService;
import com.netcompany.demo.utils.Constants;
import com.netcompany.demo.view.MemberViewActivityView;
import com.netcompany.demo.view.OrganizerUpdateActivityView;
import com.netcompany.demo.view.OrganizerViewActivityView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ActivityController extends AbstractAuthorizedMenuController<ActivityModel> {
    private final ActivityService activityService;
    private final UserService userService;
    private final DefinedItem signUpActivityItem;
    private final DefinedItem cancelActivityItem;
    private final DefinedItem updateActivityItem;
    private final OrganizerUpdateActivityView organizerUpdateActivityView;
    private Activity currentActivity;
    private AbstractView<ActivityModel> memberViewActivityView;
    private AbstractView<ActivityModel> organizerViewActivityView;
    private boolean isViewMode = true;

    public ActivityController(ApplicationContext applicationContext) {
        super(applicationContext, null, new ActivityModel(), null);
        this.activityService = new ActivityService(applicationContext.getConnection());
        this.userService = new UserService(applicationContext.getConnection());
        this.memberViewActivityView = new MemberViewActivityView(applicationContext.getConsole());
        this.organizerViewActivityView = new OrganizerViewActivityView(applicationContext.getConsole());
        this.organizerUpdateActivityView = new OrganizerUpdateActivityView(applicationContext.getConsole());
        this.signUpActivityItem = new DefinedItem("Sign up", Constants.SIGNUP_ITEM);
        this.cancelActivityItem = new DefinedItem("Cancel", Constants.CANCEL_ITEM);
        this.updateActivityItem = new DefinedItem("Update activity", Constants.UPDATE_ITEM);
    }

    @Override
    protected Action handleOtherEvent(View sender, EventArg arg) {
        if (arg instanceof ActivityUpdateEventArg) {
            ActivityUpdateEventArg activityUpdateArg = (ActivityUpdateEventArg) arg;
            this.handleUpdateActivity(activityUpdateArg);
            return new ViewAction(this.getView());
        } else if (arg == null && sender == this.organizerUpdateActivityView) {
            this.setEditMode(false);
            return new ViewAction(this.getView());
        }
        return super.handleOtherEvent(sender, arg);
    }

    @Override
    protected Action handleGeneralMenuEvent(View sender, GeneralMenuEventArg menuArg) {
        if (menuArg.getSelectedMenuItem() instanceof DefinedItem) {
            DefinedItem definedItem = (DefinedItem) menuArg.getSelectedMenuItem();
            return this.handleDefinedItem(definedItem);
        }
        return super.handleGeneralMenuEvent(sender, menuArg);
    }

    @Override
    protected boolean initiateModel() {
        Activity activity = this.getNavigationParam(Constants.ACTIVITY_ITEM);
        if (activity != null) {
            this.currentActivity = this.activityService.getActivityById(activity.getId());
            this.prepareModel();
            this.chooseView();
            return true;
        }
        return false;
    }

    private void setEditMode(boolean isEditMode) {
        if (isEditMode) {
            this.isViewMode = false;
            this.setView(this.organizerUpdateActivityView);
            this.getModel().getMenuItems().clear();
            this.getModel().setSuccess(null);
            this.getModel().setMessage("");
        } else {
            this.isViewMode = true;
            this.setView(this.organizerViewActivityView);
            this.getModel().getMenuItems().clear();
            this.getModel().getMenuItems().add(this.updateActivityItem);
        }
    }

    private void handleUpdateActivity(ActivityUpdateEventArg activityUpdateArg) {
        List<String> errorMessages = new ArrayList<>();
        Activity updatingActivity = this.currentActivity.clone();
        if (this.validateValues(updatingActivity, activityUpdateArg, errorMessages)) {
            this.activityService.updateActivity(updatingActivity, this.getIdentity());
            this.currentActivity = updatingActivity;
            this.getModel().setSuccess(true);
            errorMessages.add("_ Update activity successfully. Please press enter to continues");
        } else {
            this.getModel().setSuccess(false);
            errorMessages.add("_ Invalid input. Please press enter to update");
        }
        this.getModel().setActivity(updatingActivity);
        this.getModel().setMessage(
                errorMessages.stream()
                        .collect(Collectors.joining("\n"))
        );
    }

    private boolean validateValues(
            Activity updatingActivity
            , ActivityUpdateEventArg activityUpdateArg
            , List<String> errorMessages
    ) {
        boolean isValid = true;
        isValid &= this.validateTitle(updatingActivity, activityUpdateArg.getNewTitle(), errorMessages);
        isValid &= this.validateDescription(updatingActivity, activityUpdateArg.getNewDescription(), errorMessages);
        isValid &= this.validateDate(updatingActivity, activityUpdateArg.getNewDate(), errorMessages);
        return isValid;
    }

    private boolean validateDate(Activity newActivity, String newDate, List<String> errorMessages) {
        if (newDate != null && !newDate.isEmpty()) {
            Pattern datePattern = Pattern.compile("(\\d{4})/(\\d{1,2})/(\\d{1,2})");
            Matcher matcher = datePattern.matcher(newDate);
            if (!matcher.find()) {
                errorMessages.add("* Date must be follow format YYYY/MM/DD");
                return false;
            }
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int day = Integer.parseInt(matcher.group(3));
            Calendar calendar = Calendar.getInstance();
            //for calendar month value
            month -= 1;
            calendar.set(year, month, day);
            if (calendar.get(Calendar.DATE) != day
                    || calendar.get(Calendar.MONTH) != month
                    || calendar.get(Calendar.YEAR) != year) {
                errorMessages.add("* Date must be follow format YYYY/MM/DD");
                return false;
            }
            newActivity.setDate(calendar.toInstant().toEpochMilli());
        }
        return true;
    }

    private boolean validateDescription(Activity newActivity, String newDescription, List<String> errorMessages) {
        if (newDescription != null && !newDescription.isEmpty()) {
            if (newDescription.length() > 500) {
                errorMessages.add("* Description length cannot has more than 500 characters");
                return false;
            }
            newActivity.setDescription(newDescription);
        }
        return true;
    }

    private boolean validateTitle(Activity newActivity, String newTitle, List<String> errorMessages) {
        if (newTitle != null && !newTitle.isEmpty()) {
            if (newTitle.length() > 255) {
                errorMessages.add("* Title length cannot has more than 255 characters");
                return false;
            }
            newActivity.setTitle(newTitle);
        }
        return true;
    }

    private Action handleDefinedItem(DefinedItem definedItem) {
        System.out.println(definedItem.getAction());
        switch (definedItem.getAction()) {
            case Constants.SIGNUP_ITEM:
                this.signUpActivity(true);
                break;
            case Constants.CANCEL_ITEM:
                this.signUpActivity(false);
                break;
            case Constants.UPDATE_ITEM:
                this.setEditMode(true);
                break;
        }
        return new ViewAction(this.getView());
    }

    private void signUpActivity(boolean isSignUp) {
        Register signUpRecord = this.activityService.registerActivity(
                this.getIdentity().getUserId()
                , this.currentActivity.getId()
                , isSignUp
        );
        if (signUpRecord == null) {
            int userId = this.getIdentity().getUserId();
            this.getModel().setSignedUpList(
                    this.getModel().getSignedUpList()
                            .stream()
                            .filter(su -> su.getUserId() != userId)
                            .collect(Collectors.toList())
            );
            this.getModel().getMenuItems().remove(this.cancelActivityItem);
            this.getModel().getMenuItems().add(0, this.signUpActivityItem);
        } else {
            this.getModel().getSignedUpList().add(signUpRecord);
            this.getModel().getMenuItems().remove(this.signUpActivityItem);
            this.getModel().getMenuItems().add(0, this.cancelActivityItem);
        }
    }

    private void prepareModel() {
        List<Register> signedUpList = this.activityService.getAllRegisteredUserForActivity(this.currentActivity.getId());
        User createdByUser = this.userService.getUserById(this.currentActivity.getCreatedBy());
        User updatedByUser = this.userService.getUserById(this.currentActivity.getUpdatedBy());
        this.getModel().setActivity(this.currentActivity);
        this.getModel().setCreatedByUser(createdByUser);
        this.getModel().setUpdatedByUser(updatedByUser);
        this.getModel().setSignedUpList(signedUpList);
        this.getModel().setIdentity(this.getIdentity());
        int userId = this.getIdentity().getUserId();
        boolean isSignedUp = signedUpList.stream()
                .anyMatch(su -> su.getUserId() == userId);
        String exitTitle = "";
        this.getModel().getMenuItems().clear();
        if (this.getIdentity().getRole() == UserRole.Member) {
            exitTitle = "Back to Club's activities";
            if (isSignedUp) {
                this.getModel().getMenuItems().add(this.cancelActivityItem);
            } else {
                this.getModel().getMenuItems().add(this.signUpActivityItem);
            }
        } else {
            this.getModel().getMenuItems().add(this.updateActivityItem);
            exitTitle = "Back to List of activity";
        }
        this.getModel().setExitItem(
                ExitItem.getExitItem(exitTitle)
        );
    }

    private void chooseView() {
        if (this.getIdentity().getRole() == UserRole.Member) {
            this.setView(this.memberViewActivityView);
        } else if (isViewMode) {
            this.setView(this.organizerViewActivityView);
        } else {
            this.setView(this.organizerUpdateActivityView);
        }
    }
}
