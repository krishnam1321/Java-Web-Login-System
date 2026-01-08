package com.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updatePassword")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false); 
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("message", "New passwords do not match!");
            RequestDispatcher rd = request.getRequestDispatcher("changePassword.jsp");
            rd.forward(request, response);
            return;
        }


        try (Connection con = DBConnection.getConnection()) {
            

            String checkSql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement checkPs = con.prepareStatement(checkSql)) {
                checkPs.setString(1, username);
                checkPs.setString(2, currentPassword);
                ResultSet rs = checkPs.executeQuery();
                
                if (!rs.next()) {
                    request.setAttribute("message", "Current password is incorrect!");
                    RequestDispatcher rd = request.getRequestDispatcher("changePassword.jsp");
                    rd.forward(request, response);
                    return;
                }
            }
           String updateSql = "UPDATE users SET password = ? WHERE username = ?";
            try (PreparedStatement updatePs = con.prepareStatement(updateSql)) {
                updatePs.setString(1, newPassword);
                updatePs.setString(2, username);
                
                int rowCount = updatePs.executeUpdate();
                if (rowCount > 0) {
                    request.setAttribute("message", "Password successfully changed!");
                    
                } else {
                    request.setAttribute("message", "Error updating password.");
                }
                
                RequestDispatcher rd = request.getRequestDispatcher("changePassword.jsp");
                rd.forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Database Error: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("changePassword.jsp");
            rd.forward(request, response);
        }
    }
}
