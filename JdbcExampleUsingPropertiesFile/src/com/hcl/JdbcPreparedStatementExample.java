package com.hcl;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class JdbcPreparedStatementExample {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement prepareStmt = null;
		Scanner scan = null;
		
		try {
			FileInputStream fis = new FileInputStream("Connection.prop");
			Properties prop = new Properties();
			prop.load(fis);
			
			String dName = prop.getProperty("dName");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			String url = prop.getProperty("url");
			
			Class.forName(dName);
			conn = DriverManager.getConnection(url, user, password);
			
			scan = new Scanner(System.in);
			System.out.println("Enter the number of persons you want to enter: ");
			int n = scan.nextInt();
			prepareStmt = conn.prepareStatement("insert into persons values(?,?,?,?,?)"); 
            if (prepareStmt != null) { 
                for (int i = 1; i <= n; i++) { 
                    System.out.println("Enter " + i + " Person Details"); 
                    System.out.println("Enter Person Id : "); 
                    int personId = scan.nextInt(); 
                    System.out.println("Enter Last Name : "); 
                    String lastName = scan.next(); 
                    System.out.println("Enter First Name : "); 
                    String firstName = scan.next();
                    System.out.println("Enter Person Address : "); 
                    String address = scan.next(); 
                    System.out.println("Enter Person City : "); 
                    String city = scan.next();
                    
                    prepareStmt.setInt(1, personId); 
                    prepareStmt.setString(2, lastName); 
                    prepareStmt.setString(3, firstName); 
                    prepareStmt.setString(4, address); 
                    prepareStmt.setString(5, city); 
                    
                    int result = prepareStmt.executeUpdate(); 
                    if (result == 0) { 
                        System.out.println("Student details: are not inserted"); 
                    } else { 
                        System.out.println(result + " records(s) are  inserted"); 
                    } 
                }
            }
            
		}
		catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} 
		catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} 
		finally {
			// finally block used to close resources
			try {
				if (prepareStmt != null)
					prepareStmt.close();
			} 
			catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} 
			catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		}
	}
}
