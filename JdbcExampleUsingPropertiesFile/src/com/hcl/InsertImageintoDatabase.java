package com.hcl;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;



public class InsertImageintoDatabase {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		Scanner scan = null;
		
		try {
			
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream("Connection.prop");
			prop.load(fis);
			
			String dName = prop.getProperty("dName");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			String url = prop.getProperty("url");
			
			Class.forName(dName);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement("insert into employee values(?,?,?,?)");
			
			stmt.setInt(1, 101);
			stmt.setString(2, "K");
			stmt.setString(3, "Vanisree");
			
			String img = "C:\\Users\\nani7\\OneDrive\\Desktop\\guitar.jpg";
			File file = new File(img);
			FileInputStream stream = new FileInputStream(file);
			stmt.setBinaryStream(4, stream);
			
			stmt.executeUpdate();
			System.out.println("Image inserted");
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try{
				if(stmt!= null) {
					stmt.close();
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(conn != null) {
					conn.close();
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		
	}
}
