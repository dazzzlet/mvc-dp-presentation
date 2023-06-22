package com.netcompany.oopdemo.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DTO {
    void loadDataRow(ResultSet rs) throws SQLException;
}
