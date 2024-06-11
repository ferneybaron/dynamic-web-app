package com.fbaron.dao;

import com.fbaron.model.UserModel;

import java.util.List;

public interface UserDAO {
    void insertUser(UserModel model);
    UserModel getUserById(long id);
    UserModel getUserByUsernameAndPassword(String username, String password);
    List<UserModel> getAllUsers();
    void updateUser(long id, UserModel model);
    void deleteUser(long id);
}
