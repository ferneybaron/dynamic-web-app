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
@WebServlet("/admin/users")
public class AdminUsersServletController extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<UserModel> users = userService.getAllUsers();
        request.setAttribute("users", users);
        request.setAttribute("pageTitle", "Admin Users");
        request.setAttribute("contentPage", "/WEB-INF/view/admin/admin-users.jsp");

        request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp").forward(request, response);
    }
}
