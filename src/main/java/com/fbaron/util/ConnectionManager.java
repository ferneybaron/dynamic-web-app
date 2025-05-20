package com.fbaron.util;

import com.fbaron.config.DatabaseConfig;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author Ferney Estupinan Baron
 */
public class ConnectionManager {

    private static final DataSource dataSource = DatabaseConfig.getDataSource();

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            System.err.println("ConnectionManager failed to get connection: " + e.getMessage());
            return null;
        }
    }

}
