package com.hcl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class ReadingImageFromDatabase {

	public static void main(String[] args) {
		
		Connection connection = null;
        Statement stmt = null;

        try {
        	
        	Properties prop = new Properties();
			FileInputStream fis = new FileInputStream("Connection.prop");
			prop.load(fis);
			
        	String dName = prop.getProperty("dName");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			String url = prop.getProperty("url");
			
            Class.forName(dName);
            connection = DriverManager.getConnection(url,user,password);
            stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("select * from employee");
            
            if (rs.next()) {
            	
                InputStream is = rs.getBinaryStream("photo");
                FileOutputStream fos = new FileOutputStream("C:\\Users\\nani7\\OneDrive\\Desktop\\sample_NEW.jpg");
                int bytesRead = 0;
                byte[] buffer = new byte[4096];
                while ((bytesRead = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
                is.close();
                fos.close();
            }

            connection.close();
            System.out.println("Image created");

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
