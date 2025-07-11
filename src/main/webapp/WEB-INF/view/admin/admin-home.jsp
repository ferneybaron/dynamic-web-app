<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
    />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/css/styles.css?v=2"
    />
    <title>Admin Dashboard</title>
  </head>
  <body>
    <main class="main">
      <section class="section banner banner-section">
        <div class="container">
          <div class="login-card padding-horizontal--48">
          <h2>Welcome Admin, <span style="color: green;"><%= session.getAttribute("user") %></span></h2>
          <p>This is the admin dashboard. You have special privileges.</p>
            <div class="margin-top--24">
              <h3>Admin Actions</h3>
              <ul>
                <li><a href="${pageContext.request.contextPath}/admin/users">Manage Users</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/reports">View Reports</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
              </ul>
            </div>
          </div>
        </div>
      </section>
    </main>
    <script src="${pageContext.request.contextPath}/js/script.js?v=1"></script>
  </body>
</html>
