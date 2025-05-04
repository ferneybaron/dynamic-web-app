package com.fbaron.dao;

import com.fbaron.model.UserModel;

import java.sql.*;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static final String JDBC_URL = System.getenv("MYSQL_JDBC_URL");
    private static final String JDBC_USER = System.getenv("MYSQL_JDBC_USER");
    private static final String JDBC_PASSWORD = System.getenv("MYSQL_JDBC_PASSWORD");

    // Load the JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load JDBC driver");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    @Override
    public void insertUser(UserModel model) {
        String insertQuery = "INSERT INTO user (first_name, last_name, username, password) VALUES(?, ?, ?, ?);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, model.getFirstName());
            preparedStatement.setString(2, model.getLastName());
            preparedStatement.setString(3, model.getUsername());
            preparedStatement.setString(4, model.getPassword());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("UserDAOImpl failed to insert user: " + e.getMessage());
        }
    }

    @Override
    public UserModel getUserById(long id) {
        return null;
    }

    @Override
    public UserModel getUserByUsernameAndPassword(String username, String password) {
        UserModel userModel = null;
        String selectQuery = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userModel = new UserModel();
                userModel.setId(resultSet.getLong("id"));
                userModel.setFirstName(resultSet.getString("first_name"));
                userModel.setLastName(resultSet.getString("last_name"));
            }
        } catch (SQLException e) {
            System.err.println("UserDAOImpl failed to select user by username and password: " + e.getMessage());
        }
        return userModel;
    }

    @Override
    public List<UserModel> getAllUsers() {
        return List.of();
    }

    @Override
    public void updateUser(long id, UserModel model) {

    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public boolean userExists(String username) {

        String query = "SELECT * FROM user WHERE username = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            System.err.println("UserDAOImpl userExists failed to select user by username: " + e.getMessage());
        }
        return false;
    }
}
