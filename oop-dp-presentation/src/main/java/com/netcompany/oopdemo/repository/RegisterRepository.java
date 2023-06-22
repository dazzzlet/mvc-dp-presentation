package com.netcompany.oopdemo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netcompany.oopdemo.core.AbstractRepository;
import com.netcompany.oopdemo.dto.Activity;
import com.netcompany.oopdemo.dto.Register;

public class RegisterRepository extends AbstractRepository {
    public RegisterRepository(Connection conn) {
        super(conn);
    }

    public List<Register> getAllRegisteredUserForActivity(int activityId) {
        List<Register> result = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT user_id,activity_id,register_on " +
                    "FROM registers " +
                    "WHERE activity_id = ?");
            stmt.setInt(1, activityId);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Register register = new Register();
                register.loadDataRow(resultSet);
                result.add(register);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public Register getRegisteredUserForActivity(int userId, int activityId) {
        ResultSet resultSet = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT user_id,activity_id,register_on " +
                    "FROM registers " +
                    "WHERE activity_id = ? AND user_id = ?");
            stmt.setInt(1, activityId);
            stmt.setInt(2, userId);
            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Register register = new Register();
                register.loadDataRow(resultSet);
                return register;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void unregisterActivity(int userId, int activityId) {
        ResultSet resultSet = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE " +
                    "FROM registers " +
                    "WHERE activity_id = ? AND user_id = ?");
            stmt.setInt(1, activityId);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerActivity(Register register) {
        ResultSet resultSet = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO registers " +
                    "(user_id,activity_id,register_on) " +
                    "VALUES (?,?,?)");
            stmt.setInt(1, register.getUserId());
            stmt.setInt(2, register.getActivityId());
            stmt.setLong(3, register.getRegisterOn());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
