<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Change Password</title>
</head>
<body>
    <h2>Change Password</h2>
    <h3 style="color:red">
        <% 
           String message = (String) request.getAttribute("message");
           if(message != null) out.print(message);
        %>
    </h3>
    
    <form action="updatePassword" method="post">
        Current Password: <input type="password" name="currentPassword" required><br><br>
        New Password: <input type="password" name="newPassword" required><br><br>
        Confirm New Password: <input type="password" name="confirmPassword" required><br><br>
        <input type="submit" value="Update Password">
    </form>
    <br>
    <a href="welcome.jsp">Back to Home</a>
</body>
</html>