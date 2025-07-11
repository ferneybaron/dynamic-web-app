<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport"
  content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=1" />
<title>Access Denied</title>
<head>
    <title>Access Denied</title>
</head>
<body>
    <main class="main">
        <section class="section banner banner-section">
            <div class="container">
                <h2>Access Denied (403 Forbidden)</h2>
                <p>You do not have permission to view this page.</p>
                <p><a href="<%= request.getContextPath() %>/">Go to Landing Page</a></p>
                <p><a href="<%= request.getContextPath() %>/logout">Logout</a></p>
            </div>
        </section>
    </main>
    <script src="${pageContext.request.contextPath}/js/script.js?v=1"></script>
</body>
</html>