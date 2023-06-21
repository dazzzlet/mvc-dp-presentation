package com.netcompany.demo.repository;

import com.netcompany.demo.core.AbstractRepository;
import com.netcompany.demo.dto.User;

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
            // close connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
