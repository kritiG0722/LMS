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



/**
 * Servlet implementation class Admission
 */
@WebServlet("/Admission")
public class Admission extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CALL = null;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
		
		
		
		
		
		String url="jdbc:mysql://localhost:3306/techwizard";
		String username ="root";
		String password="KRITI@03";
		
		
		String query = "INSERT INTO admission(name, email, pass, number, address, course,radio) VALUES (?, ?, ?, ?, ?, ?, ?) "; 
		
		
		

        // Get form input
        String Username = request.getParameter("name");
        String email = request.getParameter("email");
        String Password = request.getParameter("pass"); // renamed from "pass" to "password" to avoid duplication
        String number = request.getParameter("number");
        String address = request.getParameter("address");
        String course = request.getParameter("course");
        String radio = request.getParameter("radio");
		
		try {
			
			
			
			
			//load jdbc
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			 //create connection
			Connection con = DriverManager.getConnection(url,username,password);
			
			// write statement
			PreparedStatement pstmt =con.prepareStatement(query);
			
		  
		 pstmt.setString(1, Username);
         pstmt.setString(2, email);
         pstmt.setString(3, Password);
         pstmt.setString(4, number);
         pstmt.setString(5, address);
         pstmt.setString(6, course);
         pstmt.setString(7, radio);
		 
		 
		 
		int row= pstmt.executeUpdate();
		
		if(row>0) {
			
			out.println("<h1>Admission Succsesfully Completed"+" "+"Welcome "+Username+"</h1>");
			out.println("<h3> Name:"+" "+Username+"</h3>");
			out.println("<h3> E-mail:"+" "+email+"</h3>");
			out.println("<h3> Number:"+" "+number+"</h3>");
			out.println("<h3> Course:"+" "+course+"</h3>");
			out.println("<h3>Address:"+" "+address+"</h3>");
		
			    
			
		}
		else {
			out.println("Please Try again");
		}
		
		
		//close resources
		con.close();
		pstmt.close();
		 
			
			
		}
		
		catch (Exception e) {
			 e.printStackTrace();
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    doPost(request, response);  // Handle GET like POST
	}


}
