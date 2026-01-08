<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.RequestDispatcher" %>
<html>
<head>
    <title>Welcome Page</title>
    <style>
        body { font-family: sans-serif; text-align: center; margin-top: 50px; }
        a { text-decoration: none; color: blue; font-weight: bold; }
        a:hover { color: darkblue; text-decoration: underline; }
    </style>
</head>
<body>
<%
    // 1. Session Security Gate
    // Checks if the user is actually logged in. If not, boots them back to login.
    String user = (String) session.getAttribute("username");
    if (user == null) {
        request.setAttribute("error", "Session expired. Please login again.");
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
        return; // Stops the rest of the page from loading
    }
%>

    <h2>Welcome, <%= user %>!</h2>
    <p>Login successful using JDBC and Jakarta EE.</p>
    <hr style="width: 50%;">

    <br>
    <a href="changePassword.jsp">Change Password</a>
    <br><br>
    
    <a href="logout">Logout</a>

</body>
</html>