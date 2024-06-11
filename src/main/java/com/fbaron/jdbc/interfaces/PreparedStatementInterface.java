package com.fbaron.jdbc.interfaces;

import java.sql.*;

/**
 * @author Ferney Estupinan Baron
 */
public class PreparedStatementInterface {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/fbank";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "rootpassword";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager
                    .getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Execute an insert query
            String insertQuery = "INSERT INTO user (first_name, last_name, username, password) VALUES(?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            // Set parameters
            preparedStatement.setString(1, "John");
            preparedStatement.setString(2, "Doe");
            preparedStatement.setString(3, "john_doe");
            preparedStatement.setString(4, "1234");
            preparedStatement.executeUpdate();

            System.out.println("Record inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
