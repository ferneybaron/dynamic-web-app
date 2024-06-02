package com.fbaron.model;

import java.sql.*;

/**
 * @author Ferney Estupinan Baron
 */
public class UserModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public UserModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel login(String username, String password) {
        UserModel userModel = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/fbank", "root", "rootpassword");

            String query = "SELECT * FROM user WHERE username = ? AND password = ?";

            PreparedStatement preparedStatement =  connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userModel = new UserModel();
                userModel.setId(resultSet.getLong("id"));
                userModel.setFirstName(resultSet.getString("first_name"));
                userModel.setLastName(resultSet.getString("last_name"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("LoginServlet doPost error: " + e.getMessage());
        }

        return userModel;

    }

}
