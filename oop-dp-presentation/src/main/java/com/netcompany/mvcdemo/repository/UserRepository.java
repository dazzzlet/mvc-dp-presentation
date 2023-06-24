package com.netcompany.mvcdemo.repository;

import com.netcompany.mvcdemo.core.AbstractRepository;
import com.netcompany.mvcdemo.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public User getUserById(int userId) {
        User user = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT user_id,username,firstname,role,bio " +
                    "FROM users " +
                    "WHERE user_id = ?");
            stmt.setInt(1, userId);
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

    public void createUser(User user) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO users (username, password, firstname, role, bio) " +
                    "VALUES(?, ?, ?, ?, ?)");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFirstName());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getBio());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(int userId, String firstName, String bio) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE users SET firstname =?, bio=?" +
                    "WHERE user_id = ?");
            stmt.setString(1, firstName);
            stmt.setString(2, bio);
            stmt.setInt(3, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
