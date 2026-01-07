<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
    <h2>Login Page</h2>
    <h3 style="color:red">
        <% 
           String error = (String) request.getAttribute("error");
           if(error != null) out.print(error);
        %>
    </h3>
    
    <form action="login" method="post">
        Username: <input type="text" name="username"><br>
        Password: <input type="password" name="password"><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>