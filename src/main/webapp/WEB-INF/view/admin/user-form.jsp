<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fbaron.model.UserModel" %>

<%
    UserModel editUser = (UserModel) request.getAttribute("editUser");
    List<String> errors = (List<String>) request.getAttribute("errors");
    String successMessage = request.getParameter("success");
    String errorMessage = request.getParameter("error");
    
    boolean isEditMode = editUser != null && editUser.getId() != null;
%>

<section class="section banner banner-section">
    <div class="container">
        <!-- Success/Error Messages -->
        <% if (successMessage != null) { %>
            <div class="login-card padding-horizontal--24 padding-top--12 padding-bottom--12 margin-bottom--24" style="background-color: var(--color-green-100); border: 1px solid var(--color-green-400);">
                <p style="color: var(--color-green-600); margin: 0;"><%= successMessage %></p>
            </div>
        <% } %>
        <% if (errorMessage != null) { %>
            <div class="login-card padding-horizontal--24 padding-top--12 padding-bottom--12 margin-bottom--24" style="background-color: var(--color-red-100); border: 1px solid var(--color-red-400);">
                <p style="color: var(--color-red-600); margin: 0;"><%= errorMessage %></p>
            </div>
        <% } %>
        <% if (errors != null && !errors.isEmpty()) { %>
            <div class="login-card padding-horizontal--24 padding-top--12 padding-bottom--12 margin-bottom--24" style="background-color: var(--color-red-100); border: 1px solid var(--color-red-400);">
                <ul style="color: var(--color-red-600); margin: 0; padding-left: 20px;">
                    <% for (String error : errors) { %>
                        <li><%= error %></li>
                    <% } %>
                </ul>
            </div>
        <% } %>

        <!-- User Form -->
        <div class="login-card padding-horizontal--48 padding-top--24 padding-bottom--24">
            <h2 class="heading-md margin-bottom--24"><%= isEditMode ? "Update User" : "Create New User" %></h2>
            
            <form class="login-form" action="${pageContext.request.contextPath}/admin/users/form" method="post">
                <input type="hidden" name="action" value="<%= isEditMode ? "update" : "create" %>">
                <% if (isEditMode && editUser.getId() != null) { %>
                    <input type="hidden" name="userId" value="<%= editUser.getId() %>">
                <% } %>
                
                <div class="padding-bottom--12">
                    <label for="firstName" style="display: block; margin-bottom: 6px; font-weight: 500;">First Name</label>
                    <input
                        type="text"
                        id="firstName"
                        name="firstName"
                        placeholder="First Name"
                        value="<%= editUser != null && editUser.getFirstName() != null ? editUser.getFirstName() : "" %>"
                        required
                        style="padding: 10px; width: 100%; border: 1px solid var(--color-white-200); border-radius: 4px;"
                    />
                </div>
                
                <div class="padding-bottom--12">
                    <label for="lastName" style="display: block; margin-bottom: 6px; font-weight: 500;">Last Name</label>
                    <input
                        type="text"
                        id="lastName"
                        name="lastName"
                        placeholder="Last Name"
                        value="<%= editUser != null && editUser.getLastName() != null ? editUser.getLastName() : "" %>"
                        required
                        style="padding: 10px; width: 100%; border: 1px solid var(--color-white-200); border-radius: 4px;"
                    />
                </div>
                
                <div class="padding-bottom--12">
                    <label for="username" style="display: block; margin-bottom: 6px; font-weight: 500;">Username</label>
                    <input
                        type="text"
                        id="username"
                        name="username"
                        placeholder="Username"
                        value="<%= editUser != null && editUser.getUsername() != null ? editUser.getUsername() : "" %>"
                        required
                        style="padding: 10px; width: 100%; border: 1px solid var(--color-white-200); border-radius: 4px;"
                    />
                </div>
                
                <div class="padding-bottom--12">
                    <label for="password" style="display: block; margin-bottom: 6px; font-weight: 500;">
                        Password <%= isEditMode ? "(leave blank to keep current password)" : "" %>
                    </label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        placeholder="Password"
                        <%= isEditMode ? "" : "required" %>
                        style="padding: 10px; width: 100%; border: 1px solid var(--color-white-200); border-radius: 4px;"
                    />
                </div>
                
                <% if (!isEditMode) { %>
                    <div class="padding-bottom--12">
                        <label for="confirmPassword" style="display: block; margin-bottom: 6px; font-weight: 500;">Confirm Password</label>
                        <input
                            type="password"
                            id="confirmPassword"
                            name="confirmPassword"
                            placeholder="Confirm Password"
                            required
                            style="padding: 10px; width: 100%; border: 1px solid var(--color-white-200); border-radius: 4px;"
                        />
                    </div>
                <% } %>
                
                <div class="padding-bottom--12">
                    <label for="role" style="display: block; margin-bottom: 6px; font-weight: 500;">Role</label>
                    <select
                        id="role"
                        name="role"
                        required
                        style="padding: 10px; width: 100%; border: 1px solid var(--color-white-200); border-radius: 4px;"
                    >
                        <option value="">Select Role</option>
                        <option value="ADMIN" <%= editUser != null && "ADMIN".equals(editUser.getRole()) ? "selected" : "" %>>ADMIN</option>
                        <option value="USER" <%= editUser != null && "USER".equals(editUser.getRole()) ? "selected" : "" %>>USER</option>
                    </select>
                </div>
                
                <div class="padding-top--12">
                    <button class="btn btn-darken btn-inline login-btn" type="submit">
                        <%= isEditMode ? "Update User" : "Create User" %>
                    </button>
                </div>
                <div class="padding-top--12">
                    <a href="${pageContext.request.contextPath}/admin/users"
                        class="btn btn-darken btn-inline login-btn"
                        style="text-decoration: none;">
                        Cancel
                    </a>
                </div>
            </form>
        </div>
    </div>
</section>
