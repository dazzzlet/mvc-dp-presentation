package com.netcompany.demo.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DTO {
    void loadDataRow(ResultSet rs) throws SQLException;
}
