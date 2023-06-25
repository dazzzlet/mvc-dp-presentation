package com.netcompany.demo.menu.member.user;

import java.io.Console;

import com.netcompany.demo.core.ConsoleContext;
import com.netcompany.demo.dto.User;
import com.netcompany.demo.menu.common.AbstractUserMenuItem;
import com.netcompany.demo.service.UserService;
import com.netcompany.demo.utils.ConsoleUtils;

public class UpdateProfileMenuItem extends AbstractUserMenuItem {
    private final UserService userService;

    public UpdateProfileMenuItem(ConsoleContext appCtx) {
        super(appCtx);
        this.userService = new UserService(appCtx.getConnection());
    }

    @Override
    public String getItemName() {
        return "Update profile";
    }

    @Override
    public void launch() {
        ConsoleUtils.cleanConsole();
        Console console = this.appCtx.getConsole();
        String newBio = this.getUser().getBio();
        if (newBio == null) {
            newBio = "";
        }
        while (true) {
            System.out.println("Update my profile " + this.getUser().getUsername());
            System.out.println("      ---");
            System.out.print(
                    String.format("Bio (%s): ", newBio)
            );
            newBio = console.readLine();
            User newUser = this.getUser().clone();
            ConsoleUtils.cleanConsole();
            if (this.validateValues(newUser, newBio)) {
                this.userService.updateUser(newUser);
                this.setUser(newUser);
                System.out.println("_ Update user successfully. Please press enter to continues");
                console.readLine();
                ConsoleUtils.cleanConsole();
                return;
            }
            newBio = newUser.getBio();
            System.out.println("_ Invalid input. Please press enter to update");
        }
    }

    private boolean validateValues(User newUser, String newBio) {
        boolean isValid = true;
        isValid &= this.validateBio(newUser, newBio);
        return isValid;
    }

    private boolean validateBio(User newUser, String newBio) {
        if (newBio != null && !newBio.isEmpty()) {
            if (newBio.length() > 500) {
                System.out.println("* User bio length cannot has more than 500 characters");
                return false;
            }
            newUser.setBio(newBio);
        }
        return true;
    }
}
