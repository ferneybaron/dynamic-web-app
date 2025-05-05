<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fbaron.model.UserModel" %>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport"
  content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=1" />
<title>Register</title>
</head>

<body>

<!-- Main Section -->
<main class="main">
  <section class="section banner banner-section">
    <div class="container">
      <div class="login-card padding-horizontal--48">
        <h2>FBank Account Registration</h2>
        <p class="paragraph">Create your account</p>

        <%-- Get Attributes from the request --%>
        <%
            List<String> errors = (List<String>) request.getAttribute("errors");
            UserModel userModel = (UserModel) request.getAttribute("userModel");

            String errorMessage = (String) request.getAttribute("errorMessage");
            String successMessage = (String) request.getAttribute("successMessage");

            if (errorMessage != null) {
        %>
            <p style="color: red;"><%= errorMessage %></p>
        <%
            } else if (successMessage != null) {
        %>
            <p style="color: green;"><%= successMessage %></p>
        <%
            }
            if (errors != null && !errors.isEmpty()) {
        %>
            <ul style="color: red;">
            <% for (String error : errors) { %>
                <li><%= error %></li>
            <% } %>
            </ul>
        <% } %>

        <!-- Registration Form -->
        <form id="registerForm" class="login-form" action="users" method="post">
          <div class="field">
            <label for="firstName">First Name</label>
            <input
              type="text"
              name="firstName"
              placeholder="First Name"
              value="<%= userModel != null ? userModel.getFirstName() : "" %>"
              required
            />
          </div>
          <div class="field">
            <label for="lastName">Last Name</label>
            <input
              type="text"
              name="lastName"
              placeholder="Last Name"
              value="<%= userModel != null ? userModel.getLastName() : "" %>"
              required
            />
          </div>
          <div class="field">
            <label for="username">Username</label>
            <input
              type="text"
              name="username"
              placeholder="Username"
              value="<%= userModel != null ? userModel.getUsername() : "" %>"
              required
            />
          </div>
          <div class="field">
            <label for="password">Password</label>
            <input
              type="password"
              name="password"
              placeholder="Password"
              required
            />
          </div>
          <div class="field">
            <label for="confirmPassword">Confirm Password</label>
            <input
              type="password"
              name="confirmPassword"
              placeholder="Confirm Password"
              required
            />
          </div>
          <div class="padding-top--12">
            <button class="btn btn-darken btn-inline login-btn" type="submit">
              Register
            </button>
          </div>
          <div class="footer-link padding-top--12">
            <span>Already have an account? <a href="login">Login</a></span>
          </div>
        </form>

      </div>
    </div>
  </section>
</main>

<script src="${pageContext.request.contextPath}/js/script.js?v=1"></script>
</body>

</html>