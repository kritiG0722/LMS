package com.TechWizard;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/register1")
public class register1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Get form input
        String username = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("pass"); // renamed from "pass" to "password" to avoid duplication
        String number = request.getParameter("number");
        String address = request.getParameter("address");
        String course = request.getParameter("course");

       try (PrintWriter out = response.getWriter()) {
            // Database connection details
        
            String url = "jdbc:mysql://localhost:3306/techwizard";
            String dbUser = "root";
            String dbPassword = "KRITI@03";

            String sql = "INSERT INTO registration (name, email, pass, number, address, course) VALUES (?, ?, ?, ?, ?, ?)";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Load JDBC driver

                Connection con = DriverManager.getConnection(url, dbUser, dbPassword);
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setString(3, password);
                stmt.setString(4, number);
                stmt.setString(5, address);
                stmt.setString(6, course); 

                int rowsInserted = stmt.executeUpdate();
                
                
                if (rowsInserted > 0) {
                    out.println("<h2>Registration Successful! Welcome, " + username + ".</h2>");
                    out.println("<a href='login.html'>Go to Sign In</a>");
                } else {
                    out.println("<h2>Registration Failed. Please try again.</h2>");
                }

                stmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<h3>Error: " + e.getMessage() + "</h3>");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Reuse doGet for POST
    }
}
