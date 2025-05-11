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

    public void registerUser(UserModel userModel) {
        userDAO.insertUser(userModel);
    }

    public List<String> validateUser(String username, String password) {
        List<String> errors = new ArrayList<>();

        if (username == null || username.isBlank()) {
            errors.add("Username is required");
        }

        if (password == null || password.isBlank()) {
            errors.add("Password is required");
        }

        return errors;
    }

    public UserModel authenticateUser(String username, String password) {
        UserModel user = userDAO.getUserByUsername(username);
        if (user == null) return null;

        return PasswordUtil.matches(password, user.getPassword()) ? user : null;
    }

}
