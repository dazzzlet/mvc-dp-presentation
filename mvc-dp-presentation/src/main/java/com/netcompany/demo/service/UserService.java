package com.netcompany.demo.service;

import com.netcompany.demo.dto.User;
import com.netcompany.demo.repository.UserRepository;
import com.netcompany.demo.utils.SecurityUtils;

import java.sql.Connection;
import java.util.List;

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

    public User getUserById(Integer userId) {
        if (userId != null) {
            return this.userRepository.getUserById(userId);
        }
        return null;
    }

    public void updateUser(User newUser) {
        this.userRepository.update(newUser);
    }

    public List<User> getAllUsers() {
        return this.userRepository.getAllUsers();
    }
}
