package com.netcompany.demo.menu.organizer;

import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.dto.Activity;
import com.netcompany.demo.menu.common.AbstractActivityMenuItem;
import com.netcompany.demo.service.ActivityService;
import com.netcompany.demo.utils.ConsoleUtils;
import com.netcompany.demo.utils.StringUtils;

import java.io.Console;
import java.time.Instant;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateActivityMenuItem extends AbstractActivityMenuItem {
    private final ActivityService activityService;
    private Activity activity;

    public UpdateActivityMenuItem(ConsoleContext appCtx) {
        super(appCtx);
        this.activityService = new ActivityService(appCtx.getConnection());
    }

    @Override
    public Activity getActivity() {
        return activity;
    }

    @Override
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String getItemName() {
        return "Update activity";
    }

    @Override
    public void launch() {
        ConsoleUtils.cleanConsole();
        Console console = this.appCtx.getConsole();
        String newTitle = this.activity.getTitle();
        String newDescription = this.activity.getDescription();
        String newDate = StringUtils.formatDate(this.activity.getDateValue(), "yyyy/MM/dd");
        while (true) {
            System.out.println("Update activity");
            System.out.println("      ---");
            System.out.print(
                    String.format("Title (%s): ", newTitle)
            );
            newTitle = console.readLine();
            System.out.print(
                    String.format("Description (%s): ", newDescription)
            );
            newDescription = console.readLine();
            System.out.print(
                    String.format("Date (%s): ", newDate)
            );
            newDate = console.readLine();
            Activity newActivity = this.activity.clone();
            ConsoleUtils.cleanConsole();
            if (this.validateValues(newActivity, newTitle, newDescription, newDate)) {
                this.activityService.updateActivity(newActivity, appCtx.getIdentity());
                this.activity = newActivity;
                System.out.println("_ Update activity successfully. Please press enter to continues");
                console.readLine();
                ConsoleUtils.cleanConsole();
                return;
            }
            newTitle = newActivity.getTitle();
            newDescription = newActivity.getDescription();
            newDate = StringUtils.formatDate(newActivity.getDateValue(), "yyyy/MM/dd");
            System.out.println("_ Invalid input. Please press enter to update");
        }
    }

    private boolean validateValues(Activity newActivity, String newTitle, String newDescription, String newDate) {
        boolean isValid = true;
        isValid &= this.validateTitle(newActivity, newTitle);
        isValid &= this.validateDescription(newActivity, newDescription);
        isValid &= this.validateDate(newActivity, newDate);
        return isValid;
    }

    private boolean validateDate(Activity newActivity, String newDate) {
        if (newDate != null && !newDate.isEmpty()) {
            Pattern datePattern = Pattern.compile("(\\d{4})/(\\d{1,2})/(\\d{1,2})");
            Matcher matcher = datePattern.matcher(newDate);
            if (!matcher.find()) {
                System.out.println("* Date must be follow format YYYY/MM/DD");
                return false;
            }
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int day = Integer.parseInt(matcher.group(3));
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            if (calendar.get(Calendar.DATE) != day
                    || calendar.get(Calendar.MONTH) != month
                    || calendar.get(Calendar.YEAR) != year) {
                System.out.println("* Date must be follow format YYYY/MM/DD");
                return false;
            }
            newActivity.setDate(calendar.toInstant().toEpochMilli());
        }
        return true;
    }

    private boolean validateDescription(Activity newActivity, String newDescription) {
        if (newDescription != null && !newDescription.isEmpty()) {
            if (newDescription.length() > 500) {
                System.out.println("* Description length cannot has more than 500 characters");
                return false;
            }
            newActivity.setDescription(newDescription);
        }
        return true;
    }

    private boolean validateTitle(Activity newActivity, String newTitle) {
        if (newTitle != null && !newTitle.isEmpty()) {
            if (newTitle.length() > 255) {
                System.out.println("* Title length cannot has more than 255 characters");
                return false;
            }
            newActivity.setTitle(newTitle);
        }
        return true;
    }
}
