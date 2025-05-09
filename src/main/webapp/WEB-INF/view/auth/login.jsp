<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta
            name="viewport"
            content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
    />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=1" />
    <title>Login</title>
</head>
<body>

    <!-- Main Section -->
    <main class="main">
      <section class="section banner banner-section">
        <div class="container">

          <div class="login-card padding-horizontal--48">
            <h2>Welcome Back</h2>
            <p class="paragraph">Please enter your FBank credentials</p>

            <div class="margin-bottom--12 content-center">
            <%-- Get Attributes from the request --%>
            <%
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
            %>


            <form class="login-form" action="login" method="post">
              <div class="field">
                <label for="username">Username</label>
                <input
                  type="text"
                  name="username"
                  placeholder="Enter your username"
                  required
                />
              </div>
              <div class="field">
                <label for="password">Password</label>
                <input
                  type="password"
                  name="password"
                  placeholder="Enter your password"
                  required
                />
              </div>

              <div class="padding-top--12">
                <button
                  class="btn btn-darken btn-inline login-btn"
                  type="submit"
                >
                  Login
                </button>
              </div>
              <div class="footer-link padding-top--12">
                <span>
                    Don't have an account?
                    <a href="register">Register</a>
                </span>
              </div>
            </form>

          </div>

        </div>
      </section>
    </main>

<script src="${pageContext.request.contextPath}/js/script.js?v=1"></script>
</body>
</html>