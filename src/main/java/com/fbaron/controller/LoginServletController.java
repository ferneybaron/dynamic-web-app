package com.fbaron.controller;

import com.fbaron.model.UserModel;
import com.fbaron.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author Ferney Estupinan Baron
 */
@WebServlet("/login")
public class LoginServletController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserModel userModel = new UserService().authenticateUser(username, password);

        if (userModel != null) {
            String successMessage = "Your login was successful " + userModel.getFirstName();
            request.setAttribute("successMessage", successMessage);
        } else {
            String errorMessage = "Invalid username or password, please try again.";
            request.setAttribute("errorMessage", errorMessage);
        }
        request.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(request, response);
    }

}
