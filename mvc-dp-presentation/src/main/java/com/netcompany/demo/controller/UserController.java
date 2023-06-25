package com.netcompany.demo.controller;

import com.netcompany.demo.core.*;
import com.netcompany.demo.dto.Register;
import com.netcompany.demo.dto.User;
import com.netcompany.demo.enums.UserRole;
import com.netcompany.demo.event.GeneralMenuEventArg;
import com.netcompany.demo.event.UserUpdateEventArg;
import com.netcompany.demo.model.UserModel;
import com.netcompany.demo.mvc.AbstractAuthorizedMenuController;
import com.netcompany.demo.service.ActivityService;
import com.netcompany.demo.service.UserService;
import com.netcompany.demo.utils.Constants;
import com.netcompany.demo.view.OrganizerUpdateUserView;
import com.netcompany.demo.view.OrganizerViewUserView;
import com.netcompany.demo.view.UpdateUserProfileView;
import com.netcompany.demo.view.ViewUserProfileView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserController extends AbstractAuthorizedMenuController<UserModel> {
    private final UserService userService;
    private final ActivityService activityService;
    private final OrganizerViewUserView organizerViewUserView;
    private final OrganizerUpdateUserView organizerUpdateUserView;
    private final ViewUserProfileView viewUserProfileView;
    private final UpdateUserProfileView updateUserProfileView;
    private final DefinedItem organizerUpdateItem;
    private final DefinedItem memberUpdateItem;
    private User currentUser;
    private boolean isViewMode = true;

    public UserController(
            ApplicationContext applicationContext
    ) {
        super(applicationContext, null, new UserModel(), "Back to List of user");
        this.userService = new UserService(applicationContext.getConnection());
        this.activityService = new ActivityService(applicationContext.getConnection());
        //views
        this.organizerViewUserView = new OrganizerViewUserView(applicationContext.getConsole());
        this.organizerUpdateUserView = new OrganizerUpdateUserView(applicationContext.getConsole());
        this.viewUserProfileView = new ViewUserProfileView(applicationContext.getConsole());
        this.updateUserProfileView = new UpdateUserProfileView(applicationContext.getConsole());
        //menu items
        this.organizerUpdateItem = new DefinedItem("Update user", Constants.UPDATE_ITEM);
        this.memberUpdateItem = new DefinedItem("Update profile", Constants.UPDATE_ITEM);
    }

    @Override
    protected boolean initiateModel() {
        User user = this.getNavigationParam(Constants.USER_ITEM);
        Integer userId = null;
        if (this.getIdentity().getRole() == UserRole.Member) {
            userId = this.getIdentity().getUserId();
        } else if (user != null) {
            userId = user.getId();
        }
        if (userId != null) {
            this.currentUser = this.userService.getUserById(userId);
            List<Register> signedUpRecord = this.activityService.getAllRegisteredActivityForUser(userId);
            this.getModel().setUser(this.currentUser);
            this.getModel().setRegisterRecords(signedUpRecord);
            this.setEditMode(false);
            return true;
        }
        return false;
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
    protected Action handleOtherEvent(View sender, EventArg arg) {
        if (arg instanceof UserUpdateEventArg) {
            UserUpdateEventArg userUpdateArg = (UserUpdateEventArg) arg;
            this.handleUpdateUser(userUpdateArg);
            return new ViewAction(this.getView());
        }
        return super.handleOtherEvent(sender, arg);
    }

    private void handleUpdateUser(UserUpdateEventArg userUpdateArg) {
        List<String> errorMessages = new ArrayList<>();
        User updatingUser = this.currentUser.clone();
        if (this.validateValues(updatingUser, userUpdateArg, errorMessages)) {
            this.userService.updateUser(updatingUser);
            this.currentUser = updatingUser;
            this.getModel().setSuccess(true);
            errorMessages.add("_ Update user successfully. Please press enter to continues");
        } else {
            this.getModel().setSuccess(false);
            errorMessages.add("_ Invalid input. Please press enter to update");
        }
        this.getModel().setUser(updatingUser);
        this.getModel().setMessage(
                errorMessages.stream()
                        .collect(Collectors.joining("\n"))
        );
    }

    private boolean validateValues(User updatingUser, UserUpdateEventArg userUpdateArg, List<String> errorMessages) {
        boolean isValid = true;
        isValid &= this.validateFirstname(updatingUser, userUpdateArg.getNewFirstName(), errorMessages);
        isValid &= this.validateBio(updatingUser, userUpdateArg.getNewBio(), errorMessages);
        return isValid;
    }

    private boolean validateBio(User newUser, String newBio, List<String> errorMessages) {
        if (newBio != null && !newBio.isEmpty()) {
            if (newBio.length() > 500) {
                errorMessages.add("* User bio length cannot has more than 500 characters");
                return false;
            }
            newUser.setBio(newBio);
        }
        return true;
    }

    private boolean validateFirstname(User newUser, String newFirstName, List<String> errorMessages) {
        if (newFirstName != null && !newFirstName.isEmpty()) {
            if (newFirstName.length() > 255) {
                errorMessages.add("* User first length cannot has more than 255 characters");
                return false;
            }
            newUser.setFirstname(newFirstName);
        }
        return true;
    }

    private Action handleDefinedItem(DefinedItem definedItem) {
        switch (definedItem.getAction()) {
            case Constants.UPDATE_ITEM:
                this.setEditMode(true);
                return new ViewAction(this.getView());
        }
        return EmptyAction.getEmptyAction();
    }

    private void chooseView() {
        this.getModel().getMenuItems().clear();
        if (isViewMode) {
            if (this.getIdentity().getRole() == UserRole.Member) {
                this.getModel().getMenuItems().add(this.memberUpdateItem);
                this.setView(this.viewUserProfileView);
            } else {
                this.getModel().getMenuItems().add(this.organizerUpdateItem);
                this.setView(this.organizerViewUserView);
            }
        } else {
            if (this.getIdentity().getRole() == UserRole.Member) {
                this.setView(this.updateUserProfileView);
            } else {
                this.setView(this.organizerUpdateUserView);
            }
        }
    }

    private void setEditMode(boolean isEditMode) {
        if (isEditMode) {
            this.isViewMode = false;
            this.getModel().setSuccess(null);
            this.getModel().setMessage("");
        } else {
            this.isViewMode = true;
            this.getModel().getMenuItems().clear();
        }
        this.chooseView();
    }

}
