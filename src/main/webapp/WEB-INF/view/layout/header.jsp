<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.fbaron.model.UserModel" %>

<%-- Get Attributes from the request --%>
<%
    UserModel userModel = (UserModel) request.getAttribute("loggedInUser");
    String role = (String) request.getAttribute("role");

    String pageTitle = (String) request.getAttribute("pageTitle");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta
            name="viewport"
            content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
    />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v2" />
    <title><%= pageTitle %></title>
</head>
<body>