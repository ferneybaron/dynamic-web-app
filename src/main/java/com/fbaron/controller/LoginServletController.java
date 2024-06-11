package com.fbaron.controller;

import com.fbaron.dao.UserDAOImpl;
import com.fbaron.model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

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
        String loginMessage = null;

        UserModel userModel = new UserDAOImpl().getUserByUsernameAndPassword(username, password);

        if (userModel != null) {
            loginMessage = "Your login was successful " + userModel.getFirstName();
        } else {
            loginMessage = "Invalid username or password, please try again.";
        }
        request.setAttribute("loginMessage", loginMessage);
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

}
