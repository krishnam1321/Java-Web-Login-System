package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/authdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; 

    public static Connection getConnection() {
        Connection con = null;
      try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	String urlWithParams = URL + "?useSSL=false&allowPublicKeyRetrieval=true";
        	con = DriverManager.getConnection(urlWithParams, USER, PASSWORD);
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            
            SQLWarning warning = con.getWarnings();
            if (warning != null) {
                System.out.println("SQL Warning: " + warning.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
        return con;
    }

    public static void main(String[] args) {
        Connection testCon = getConnection();
        if (testCon != null) {
            System.out.println("SUCCESS: Eclipse is connected to MySQL!");
        } else {
            System.out.println("FAILURE: Check your password or JAR file.");
        }
    }
}
