<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.RequestDispatcher" %>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
<%
    String user = (String) session.getAttribute("username");
    if (user == null) {
        request.setAttribute("error", "Session expired. Please login again.");
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
        return;
    }
%>
    <h2>Welcome, <%= user %>!</h2>
    <p>Login successful using JDBC and Jakarta EE.</p>
    <br>
    <a href="logout">Logout</a>
</body>
</html>