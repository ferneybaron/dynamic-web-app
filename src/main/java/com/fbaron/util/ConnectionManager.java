package com.fbaron.util;

import com.fbaron.config.DatabaseConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Ferney Estupinan Baron
 */
public class ConnectionManager {

    private static final DataSource dataSource = DatabaseConfig.getDataSource();

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to obtain DB connection: ", e);
        }
    }

}
