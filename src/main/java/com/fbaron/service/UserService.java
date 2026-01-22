package com.fbaron.service;

import com.fbaron.dao.UserDAO;
import com.fbaron.dao.UserDAOImpl;
import com.fbaron.model.UserModel;
import com.fbaron.util.PasswordUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ferney Estupinan Baron
 */
public class UserService {

    private final UserDAO userDAO = new UserDAOImpl();

    public List<String> validateUser(UserModel userModel, String confirmPassword) {

        List<String> errors = new ArrayList<>();

        if (userModel.getFirstName() == null || userModel.getFirstName().isBlank()) {
            errors.add("First name is required");
        }

        if (userModel.getLastName() == null || userModel.getLastName().isBlank()) {
            errors.add("Last name is required");
        }

        if (userModel.getUsername() == null || userModel.getUsername().isBlank()) {
            errors.add("Username is required");
        }

        if (userModel.getPassword() == null || userModel.getPassword().isBlank()) {
            errors.add("Password is required");
        }

        if (userModel.getPassword() != null && userModel.getPassword().length() < 8) {
            errors.add("Password must be at least 8 characters");
        }

        if (!userModel.getPassword().equals(confirmPassword)) {
            errors.add("Passwords do not match");
        }

        if (userDAO.userExists(userModel.getUsername())) {
            errors.add("Username already exists");
        }

        return errors;
    }

    public void registerUser(UserModel userModel, String role) {
        String hashedPassword = PasswordUtil.hash(userModel.getPassword());
        userModel.setPassword(hashedPassword);
        userModel.setRole(role);
        userDAO.insertUser(userModel);
    }

    public UserModel authenticateUser(String username, String password) {
        UserModel user = userDAO.getUserByUsername(username);
        if (user == null) return null;

        return PasswordUtil.matches(password, user.getPassword()) ? user : null;
    }

    public List<UserModel> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public UserModel getUserById(long id) {
        return userDAO.getUserById(id);
    }

    public List<String> validateUserForCreate(UserModel userModel, String confirmPassword) {
        List<String> errors = new ArrayList<>();

        if (userModel.getFirstName() == null || userModel.getFirstName().isBlank()) {
            errors.add("First name is required");
        }

        if (userModel.getLastName() == null || userModel.getLastName().isBlank()) {
            errors.add("Last name is required");
        }

        if (userModel.getUsername() == null || userModel.getUsername().isBlank()) {
            errors.add("Username is required");
        }

        if (userModel.getPassword() == null || userModel.getPassword().isBlank()) {
            errors.add("Password is required");
        }

        if (userModel.getPassword() != null && userModel.getPassword().length() < 8) {
            errors.add("Password must be at least 8 characters");
        }

        if (userModel.getPassword() != null && confirmPassword != null && !userModel.getPassword().equals(confirmPassword)) {
            errors.add("Passwords do not match");
        }

        if (userModel.getUsername() != null && userDAO.userExists(userModel.getUsername())) {
            errors.add("Username already exists");
        }

        if (userModel.getRole() == null || userModel.getRole().isBlank()) {
            errors.add("Role is required");
        }

        return errors;
    }

    public List<String> validateUserForUpdate(UserModel userModel, long userId) {
        List<String> errors = new ArrayList<>();

        if (userModel.getFirstName() == null || userModel.getFirstName().isBlank()) {
            errors.add("First name is required");
        }

        if (userModel.getLastName() == null || userModel.getLastName().isBlank()) {
            errors.add("Last name is required");
        }

        if (userModel.getUsername() == null || userModel.getUsername().isBlank()) {
            errors.add("Username is required");
        }

        // Check if username exists for another user
        UserModel existingUser = userDAO.getUserByUsername(userModel.getUsername());
        if (existingUser != null && existingUser.getId() != userId) {
            errors.add("Username already exists");
        }

        if (userModel.getRole() == null || userModel.getRole().isBlank()) {
            errors.add("Role is required");
        }

        return errors;
    }

    public void createUser(UserModel userModel, String role) {
        String hashedPassword = PasswordUtil.hash(userModel.getPassword());
        userModel.setPassword(hashedPassword);
        userModel.setRole(role);
        userDAO.insertUser(userModel);
    }

    public void updateUser(long id, UserModel userModel) {
        // If password is provided, hash it; otherwise, set to null so it won't be updated
        if (userModel.getPassword() != null && !userModel.getPassword().isBlank()) {
            String hashedPassword = PasswordUtil.hash(userModel.getPassword());
            userModel.setPassword(hashedPassword);
        } else {
            userModel.setPassword(null);
        }
        userDAO.updateUser(id, userModel);
    }

}
