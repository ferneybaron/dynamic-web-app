package com.fbaron.dao;

import com.fbaron.model.UserModel;
import com.fbaron.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public void insertUser(UserModel model) {
        String insertQuery = "INSERT INTO user (first_name, last_name, username, password, role) VALUES(?, ?, ?, ?, ?);";
        try (Connection connection = ConnectionManager.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

                preparedStatement.setString(1, model.getFirstName());
                preparedStatement.setString(2, model.getLastName());
                preparedStatement.setString(3, model.getUsername());
                preparedStatement.setString(4, model.getPassword());
                preparedStatement.setString(5, model.getRole());
                preparedStatement.executeUpdate();

            }
        } catch (SQLException e) {
            System.err.println("UserDAOImpl failed to insert user: " + e.getMessage());
        }
    }

    @Override
    public UserModel getUserById(long id) {
        return null;
    }

    @Override
    public UserModel getUserByUsername(String username) {
        UserModel userModel = null;
        String selectQuery = "SELECT * FROM user WHERE username = ?";
        try (Connection connection = ConnectionManager.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

                preparedStatement.setString(1, username);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    userModel = new UserModel();
                    userModel.setId(resultSet.getLong("id"));
                    userModel.setFirstName(resultSet.getString("first_name"));
                    userModel.setLastName(resultSet.getString("last_name"));
                    userModel.setUsername(resultSet.getString("username"));
                    userModel.setPassword(resultSet.getString("password"));
                    userModel.setRole(resultSet.getString("role"));
                }
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
        try (Connection connection = ConnectionManager.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();

                return resultSet.next();

            }
        } catch (SQLException e) {
            System.err.println("UserDAOImpl userExists failed to select user by username: " + e.getMessage());
        }
        return false;
    }
}
