package com.fbaron.jdbc.interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Ferney Estupinan Baron
 */
public class StatementInterface {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/fbank", "root", "rootpassword");

            Statement statement = connection.createStatement();

            // Execute an update query
            String updateQuery = "UPDATE user SET first_name = 'FERNEY' WHERE id = 1";
            int rowsAffected = statement.executeUpdate(updateQuery);

            System.out.println(rowsAffected + " rows updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
