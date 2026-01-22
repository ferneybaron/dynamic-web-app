<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fbaron.model.UserModel" %>

<%
    List<UserModel> users = (List<UserModel>) request.getAttribute("users");
    String successMessage = request.getParameter("success");
    String errorMessage = request.getParameter("error");
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

        <!-- Users Table -->
        <div class="table-card padding-horizontal--48 padding-top--24 padding-bottom--24">
            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 24px; gap: 1rem;">
                <h2 class="heading-md" style="margin: 0; flex: 1;">All Users</h2>
                <a href="${pageContext.request.contextPath}/admin/users/form" 
                   class="btn btn-darken btn-inline" 
                   style="text-decoration: none; white-space: nowrap; margin-left: auto;">
                    Create New User
                </a>
            </div>
            
            <% if (users != null && !users.isEmpty()) { %>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Username</th>
                            <th>Role</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (UserModel user : users) { %>
                            <tr>
                                <td><%= user.getId() %></td>
                                <td><%= user.getFirstName() != null ? user.getFirstName() : "" %></td>
                                <td><%= user.getLastName() != null ? user.getLastName() : "" %></td>
                                <td><%= user.getUsername() != null ? user.getUsername() : "" %></td>
                                <td><%= user.getRole() != null ? user.getRole() : "" %></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/users/form?id=<%= user.getId() %>" 
                                       class="btn btn-darken" 
                                       style="padding: 0.5rem 1rem; font-size: 0.85rem; text-decoration: none;">
                                        Edit
                                    </a>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } else { %>
                <p class="paragraph">No users found.</p>
            <% } %>
        </div>
    </div>
</section>
