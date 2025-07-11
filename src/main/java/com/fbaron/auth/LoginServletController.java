package com.fbaron.auth;

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

    private final UserService userService = new UserService(); // Consider dependency injection for real apps

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String loginMessage = null;

        UserModel userModel = userService.authenticateUser(username, password);

        if (userModel != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", userModel.getUsername());
            String userRole = userModel.getRole();
            session.setAttribute("role", userRole); // Store the user's actual role

            System.out.println("User '" + userModel.getUsername() + "' with role '" + userRole + "' logged in successfully.");

            // Redirect based on the user's role
            if ("ADMIN".equals(userRole)) {
                response.sendRedirect(request.getContextPath() + "/admin/home");
            } else if ("USER".equals(userRole)) {
                response.sendRedirect(request.getContextPath() + "/user/home");
            } else {
                // Default redirect for other roles or if role is null/unrecognized
                // Redirect to the application's root, which is now handled by IndexServletController
                response.sendRedirect(request.getContextPath() + "/");
            }
        } else {
            loginMessage = "Invalid username or password, please try again.";
            request.setAttribute("loginMessage", loginMessage);
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            System.out.println("Login failed for username: " + username);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Object authenticatedUser = (session != null) ? session.getAttribute("user") : null;

        if (authenticatedUser != null) {
            // User is already logged in, redirect them away from the login page
            String role = (String) session.getAttribute("role");

            if ("ADMIN".equals(role)) {
                response.sendRedirect(request.getContextPath() + "/admin/home");
            } else if ("USER".equals(role)) {
                response.sendRedirect(request.getContextPath() + "/user/home");
            } else {
                // Default redirect for other roles or if role is null/unrecognized
                response.sendRedirect(request.getContextPath() + "/"); // Redirect to the application root
            }
            return;
        }

        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }
}
