package com.fbaron.controller;

import com.fbaron.model.UserModel;
import com.fbaron.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * @author Ferney Estupinan Baron
 */
@WebServlet("/users")
public class UserServletController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        UserModel userModel = new UserModel();
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setUsername(username);
        userModel.setPassword(password);


        UserService userService = new UserService();

        List<String> errors = userService.validateUser(userModel, confirmPassword);

        if (errors.isEmpty()) {
            String successMessage = "Your registration was successful " + userModel.getFirstName();
            request.setAttribute("successMessage", successMessage);
        } else {
            String errorMessage = "There were validation errors. Please fix them and try again.";
            request.setAttribute("errors", errors);
            request.setAttribute("userModel", userModel);
            request.setAttribute("errorMessage", errorMessage);
        }

        userService.registerUser(userModel);

        request.getRequestDispatcher("/WEB-INF/view/auth/register.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserService();
        List<UserModel> users = userService.getAllUsers();
        request.setAttribute("users", users);
        request.setAttribute("viewPage", "/WEB-INF/view/users/user-list.jsp");
        request.getRequestDispatcher("/WEB-INF/view/base-layout.jsp").forward(request, response);
    }
}
