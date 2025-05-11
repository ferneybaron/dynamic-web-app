package com.fbaron.controller;

import com.fbaron.model.UserModel;
import com.fbaron.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

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

        UserService userService = new UserService();
        List<String> errors = userService.validateUser(username, password);

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            return;
        }

        UserModel userModel = userService.authenticateUser(username, password);

        if (userModel != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            response.sendRedirect("home");
        } else {
            String errorMessage = "Invalid username or password, please try again.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Object authenticatedUser = request.getSession().getAttribute("user");

        if (authenticatedUser != null) {
            response.sendRedirect("home");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

}
