package com.netcompany.demo.repository;

import com.netcompany.demo.core.AbstractRepository;
import com.netcompany.demo.dto.Activity;
import com.netcompany.demo.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityRepository extends AbstractRepository {
    public ActivityRepository(Connection conn) {
        super(conn);
    }

    public List<Activity> getAllActivity() {
        List<Activity> result = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT activity_id,title,description" +
                    ",date,created_on,created_by" +
                    ",updated_on,updated_by,status " +
                    "FROM activities ");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Activity activity = new Activity();
                activity.loadDataRow(resultSet);
                result.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void update(Activity activity) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE activities SET " +
                    " title = ?" +
                    ",description = ?" +
                    ",date = ?" +
                    ",updated_on = ?" +
                    ",updated_by = ? " +
                    "WHERE activity_id = ?");
            stmt.setString(1, activity.getTitle());
            stmt.setString(2, activity.getDescription());
            stmt.setLong(3, activity.getDate());
            stmt.setLong(4, activity.getUpdatedOn());
            stmt.setInt(5, activity.getUpdatedBy());
            stmt.setInt(6, activity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
