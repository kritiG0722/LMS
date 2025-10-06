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
import java.sql.ResultSet;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url="jdbc:mysql://localhost:3306/techwizard";
		String username="root";
		String password="KRITI@03";
		
		String Username=request.getParameter("name");
		String Password=request.getParameter("pass");
		
	//SQL QUERY 	
	String query="SELECT * FROM registration WHERE name=? AND pass=?";
		
		
		
	response.setContentType("text/html");
    PrintWriter out = response.getWriter();
	
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Load JDBC driver

        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement stmt = con.prepareStatement(query);

        stmt.setString(1, Username);
        stmt.setString(2, Password);
       
        //ResulSet 
        ResultSet rs = stmt.executeQuery();

        

        if (rs.next()) {
            out.println("<h2>Login! Successfull! Welcome, " + Username + ".</h2>");
        } else {
            out.println("<h4>Invalid Username & Password . Please try again.</h4>");
        }

        stmt.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<h3>Error: " + e.getMessage() + "</h3>");
    }
}
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
