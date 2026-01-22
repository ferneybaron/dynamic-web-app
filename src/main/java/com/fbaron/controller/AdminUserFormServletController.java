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
@WebServlet("/admin/users/form")
public class AdminUserFormServletController extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String editId = request.getParameter("id");
        UserModel editUser = null;
        
        if (editId != null && !editId.isBlank()) {
            try {
                long id = Long.parseLong(editId);
                editUser = userService.getUserById(id);
                if (editUser == null) {
                    response.sendRedirect(request.getContextPath() + "/admin/users?error=User not found");
                    return;
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/admin/users?error=Invalid user ID");
                return;
            }
        }

        request.setAttribute("editUser", editUser);
        request.setAttribute("pageTitle", editUser != null ? "Edit User" : "Create User");
        request.setAttribute("contentPage", "/WEB-INF/view/admin/user-form.jsp");

        request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String userIdParam = request.getParameter("userId");

        if ("create".equals(action)) {
            // Create new user
            UserModel userModel = new UserModel();
            userModel.setFirstName(request.getParameter("firstName"));
            userModel.setLastName(request.getParameter("lastName"));
            userModel.setUsername(request.getParameter("username"));
            userModel.setPassword(request.getParameter("password"));
            String role = request.getParameter("role");
            userModel.setRole(role); // Set role on userModel before validation
            String confirmPassword = request.getParameter("confirmPassword");

            List<String> errors = userService.validateUserForCreate(userModel, confirmPassword);
            
            if (errors.isEmpty()) {
                userService.createUser(userModel, role);
                response.sendRedirect(request.getContextPath() + "/admin/users?success=User created successfully");
            } else {
                request.setAttribute("errors", errors);
                request.setAttribute("editUser", userModel);
                request.setAttribute("pageTitle", "Create User");
                request.setAttribute("contentPage", "/WEB-INF/view/admin/user-form.jsp");
                request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp").forward(request, response);
            }
        } else if ("update".equals(action) && userIdParam != null) {
            // Update existing user
            try {
                long userId = Long.parseLong(userIdParam);
                UserModel userModel = new UserModel();
                userModel.setFirstName(request.getParameter("firstName"));
                userModel.setLastName(request.getParameter("lastName"));
                userModel.setUsername(request.getParameter("username"));
                String password = request.getParameter("password");
                if (password != null && !password.isBlank()) {
                    userModel.setPassword(password);
                }
                userModel.setRole(request.getParameter("role"));

                List<String> errors = userService.validateUserForUpdate(userModel, userId);
                
                if (errors.isEmpty()) {
                    userService.updateUser(userId, userModel);
                    response.sendRedirect(request.getContextPath() + "/admin/users?success=User updated successfully");
                } else {
                    userModel.setId(userId);
                    request.setAttribute("errors", errors);
                    request.setAttribute("editUser", userModel);
                    request.setAttribute("pageTitle", "Edit User");
                    request.setAttribute("contentPage", "/WEB-INF/view/admin/user-form.jsp");
                    request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/admin/users?error=Invalid user ID");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/users?error=Invalid action");
        }
    }
}
