package com.netcompany.mvcdemo.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DTO {
    void loadDataRow(ResultSet rs) throws SQLException;
}
