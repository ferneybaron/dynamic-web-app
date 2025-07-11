<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.fbaron.model.UserModel" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
        <%-- Get Attributes from the request --%>
        <%
            UserModel userModel = (UserModel) request.getAttribute("loggedInUser");
            String userRole = (String) request.getAttribute("userRole");
        %>

    <h2>Welcome, <%= userModel.getUsername() %>!</h2>
    <p>Your role: <%= userRole %></p>
    <ul>
        <li><a href="<%= request.getContextPath() %>/dashboard">Dashboard</a></li>
        <li><a href="<%= request.getContextPath() %>/admin/manageUsers">Manage Users (Admin Only)</a></li>
        <li><a href="<%= request.getContextPath() %>/user/accounts">Edit Content (Editor/Admin Only)</a></li>
        <li><a href="<%= request.getContextPath() %>/logout">Logout</a></li>
    </ul>
</body>
</html>