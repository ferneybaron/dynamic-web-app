package com.fbaron.dao;

import com.fbaron.model.UserModel;
import com.fbaron.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public void insertUser(UserModel model) {
        String insertQuery = "INSERT INTO user (first_name, last_name, username, password, role) VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, model.getFirstName());
            preparedStatement.setString(2, model.getLastName());
            preparedStatement.setString(3, model.getUsername());
            preparedStatement.setString(4, model.getPassword());
            preparedStatement.setString(5, model.getRole());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("UserDAOImpl failed to insert user: " + e.getMessage());
        }
    }

    @Override
    public UserModel getUserById(long id) {
        UserModel userModel = null;
        String selectQuery = "SELECT id, first_name, last_name, username, role FROM user WHERE id = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userModel = new UserModel();
                userModel.setId(resultSet.getLong("id"));
                userModel.setFirstName(resultSet.getString("first_name"));
                userModel.setLastName(resultSet.getString("last_name"));
                userModel.setUsername(resultSet.getString("username"));
                userModel.setRole(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            System.err.println("UserDAOImpl failed to get user by id: " + e.getMessage());
        }
        return userModel;
    }

    @Override
    public UserModel getUserByUsername(String username) {
        UserModel userModel = null;
        String selectQuery = "SELECT * FROM user WHERE username = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

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
        } catch (SQLException e) {
            System.err.println("UserDAOImpl failed to select user by username and password: " + e.getMessage());
        }
        return userModel;
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<UserModel> users = new ArrayList<>();
        String selectQuery = "SELECT id, first_name, last_name, username, role FROM user ORDER BY id";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                UserModel userModel = new UserModel();
                userModel.setId(resultSet.getLong("id"));
                userModel.setFirstName(resultSet.getString("first_name"));
                userModel.setLastName(resultSet.getString("last_name"));
                userModel.setUsername(resultSet.getString("username"));
                userModel.setRole(resultSet.getString("role"));
                users.add(userModel);
            }
        } catch (SQLException e) {
            System.err.println("UserDAOImpl failed to get all users: " + e.getMessage());
        }
        return users;
    }

    @Override
    public void updateUser(long id, UserModel model) {
        String updateQuery;
        if (model.getPassword() != null && !model.getPassword().isBlank()) {
            updateQuery = "UPDATE user SET first_name = ?, last_name = ?, username = ?, role = ?, password = ? WHERE id = ?";
        } else {
            updateQuery = "UPDATE user SET first_name = ?, last_name = ?, username = ?, role = ? WHERE id = ?";
        }
        
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, model.getFirstName());
            preparedStatement.setString(2, model.getLastName());
            preparedStatement.setString(3, model.getUsername());
            preparedStatement.setString(4, model.getRole());
            
            if (model.getPassword() != null && !model.getPassword().isBlank()) {
                preparedStatement.setString(5, model.getPassword());
                preparedStatement.setLong(6, id);
            } else {
                preparedStatement.setLong(5, id);
            }
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("UserDAOImpl failed to update user: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public boolean userExists(String username) {

        String query = "SELECT * FROM user WHERE username = ?";
        try (Connection connection = ConnectionManager.getConnection();
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
