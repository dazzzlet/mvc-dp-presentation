package com.netcompany.mvcdemo.service;

import com.netcompany.mvcdemo.models.User;
import com.netcompany.mvcdemo.repository.UserRepository;
import com.netcompany.mvcdemo.utils.SecurityUtils;

import java.sql.Connection;

public class UserService {
    private final UserRepository userRepository;

    public UserService(Connection conn) {
        this.userRepository = new UserRepository(conn);
    }

    public User getUserByUsernamePassword(String username, String password) {
        User user = this.userRepository.getUserByUserName(username);
        if (user != null) {
            boolean isMatchPassword = SecurityUtils.checkPassword(password, user.getPassword());
            if (!isMatchPassword) {
                return null;
            }
        }
        return user;
    }

    public boolean isUserExisted(String username) {
        User user = this.userRepository.getUserByUserName(username);
        if (user != null) {
            return true;
        }
        return false;
    }

    public User getUserByUsername(String username) {
        User user = this.userRepository.getUserByUserName(username);
        return user;
    }

    public User getUserById(int userId) {
        User user = this.userRepository.getUserById(userId);
        return user;
    }

    public void createUser(String username, String password, String firstName, String role, String bio) {
        String hashedPassword = SecurityUtils.getHashedPassword(password);
        User user = new User(username, hashedPassword, firstName, role, bio);
        this.userRepository.createUser(user);
    }

    public void updateUser(int id, String firstName, String bio) {
        this.userRepository.updateUser(id, firstName, bio);
        ;
    }
}
