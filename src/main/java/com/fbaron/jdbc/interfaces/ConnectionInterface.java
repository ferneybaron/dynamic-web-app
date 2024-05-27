package com.fbaron.jdbc.interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Ferney Estupinan Baron
 */
public class ConnectionInterface {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/fbank", "root", "rootpassword");

            Statement statement = connection.createStatement();

            // Execute an SQL query
            String sqlQuery = "SELECT * FROM user";
            statement.execute(sqlQuery);

            System.out.println("Query executed successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
