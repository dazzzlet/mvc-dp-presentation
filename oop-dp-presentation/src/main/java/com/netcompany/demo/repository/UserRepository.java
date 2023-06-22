package com.netcompany.demo.repository;

import com.netcompany.demo.core.AbstractRepository;
import com.netcompany.demo.dto.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository extends AbstractRepository {
    public UserRepository(Connection conn) {
        super(conn);
    }

    public User getUserByUserName(String userName) {
        User user = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT user_id,username,firstname,password,role,bio " +
                    "FROM users " +
                    "WHERE username = ?");
            stmt.setString(1, userName);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                user = new User();
                user.loadDataRow(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserById(Integer userId) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT user_id,username,firstname,password,role,bio " +
                    "FROM users " +
                    "WHERE user_id = ?");
            stmt.setInt(1, userId);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                User user = new User();
                user.loadDataRow(result);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getUserByIds(List<Integer> userIds) {
        List<User> users = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            String inStatement = userIds.stream().map(id -> "?").collect(Collectors.joining(","));
            stmt = conn.prepareStatement("SELECT user_id,username,firstname,password,role,bio " +
                    "FROM users " +
                    "WHERE user_id in (" + inStatement + ")");
            for (int i = 0; i < userIds.size(); i++) {
                stmt.setInt(i + 1, userIds.get(i));
            }
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                User user = new User();
                user.loadDataRow(result);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
