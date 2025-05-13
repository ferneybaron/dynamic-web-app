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
    <title>Home</title>
  </head>
  <body>

    <%-- Get Attributes from the request --%>
    <%
        String user = (String) request.getSession().getAttribute("user");
    %>
    <!-- Main Section -->
    <main class="main">
      <section class="section banner banner-section">
        <div class="container">
          <div class="login-card padding-horizontal--48">
            <h2>Welcome Back <p style="color: green;"><%= user %>
            <div class="margin-bottom--12 content-center full-width">

            </div>
          </div>
        </div>
      </section>
    </main>

    <script src="${pageContext.request.contextPath}/js/script.js?v=1"></script>
  </body>
</html>