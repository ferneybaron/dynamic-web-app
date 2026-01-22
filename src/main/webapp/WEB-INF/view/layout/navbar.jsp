<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.fbaron.model.UserModel" %>

<%-- Get Attributes from the session --%>
<%
    String username = (session != null) ? (String) session.getAttribute("user") : null;
    String role = (session != null) ? (String) session.getAttribute("role") : null;
    boolean isLoggedIn = username != null;
%>

<header class="header" id="header">
    <nav class="navbar">
        <a href="${pageContext.request.contextPath}/" class="brand">FBaron Bank</a>
        
        <button class="burger" id="burger" type="button">
            <span class="burger-line"></span>
            <span class="burger-line"></span>
            <span class="burger-line"></span>
        </button>
        
        <div class="menu" id="menu">
            <div class="menu-inner">
                <% if (!isLoggedIn) { %>
                    <a href="${pageContext.request.contextPath}/" class="menu-link">Home</a>
                <% } else { %>
                    <% if (role.equalsIgnoreCase("ADMIN")) { %>
                        <a href="${pageContext.request.contextPath}/admin/home" class="menu-link">Home</a>
                        <a href="${pageContext.request.contextPath}/admin/users" class="menu-link">Users</a>
                        <a href="${pageContext.request.contextPath}/admin/bank-accounts" class="menu-link">Bank Accounts</a>
                    <% } else { %>
                        <a href="${pageContext.request.contextPath}/users/home" class="menu-link">Home</a>
                        <a href="${pageContext.request.contextPath}/users/profile" class="menu-link">Profile</a>
                        <a href="${pageContext.request.contextPath}/users/accounts" class="menu-link">Bank Accounts</a>
                    <% } %>
                    <a href="${pageContext.request.contextPath}/logout" class="menu-link">Logout</a>
                <% } %>
            </div>
        </div>
    </nav>
</header>
