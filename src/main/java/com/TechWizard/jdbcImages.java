package com.TechWizard;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jdbcImages{
	public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/work"; // Change database URL
        String user = "root"; // Change to your DB username
        String password = "KRITI@03"; // Change to your DB password

        String imagePath = "C:\\Users\\KRITI GUPTA\\eclips.workplace\\com.TechWizard\\src\\main\\webapp\\HTML_PAGE\\CSS_PAGE\\MEDIA\\AI.jpg"; // Change to your image path

        try (Connection conn = DriverManager.getConnection(url, user, password);
             FileInputStream fis = new FileInputStream(new File(imagePath))) {

            String sql = "INSERT INTO images (name, photo) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "Sample Image");
                pstmt.setBinaryStream(2, fis, (int) new File(imagePath).length());

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Image inserted successfully!");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}