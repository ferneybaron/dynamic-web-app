package com.fbaron.controller;

import com.fbaron.dao.UserDAOImpl;
import com.fbaron.model.UserModel;
import com.fbaron.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
        String loginMessage = null;

        UserService userService = new UserService();

        UserModel userModel = userService.authenticateUser(username, password);

        if (userModel != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            response.sendRedirect("home");
        } else {
            loginMessage = "Invalid username or password, please try again.";
            request.setAttribute("loginMessage", loginMessage);
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Object authenticatedUser =  request.getSession().getAttribute("user");

        if (authenticatedUser != null) {
            response.sendRedirect("home");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

}
