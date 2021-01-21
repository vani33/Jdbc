package com.hcl;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class JdbcExampleUsingProperties {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
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
			stmt = conn.createStatement();
			
			//Retrieving records
			
			  ResultSet rs = stmt.executeQuery("select * from persons"); 
			  while (rs.next())
			  	{ 
				  System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +
						  rs.getString(3)+ " " + rs.getString(4) + " " + rs.getString(5)); 
			  	}
			 
			
			//Inserting records
			/*
			 * scan = new Scanner(System.in); System.out.println("Enter the id"); int id =
			 * scan.nextInt(); System.out.println("Enter the lastname"); String lastName =
			 * scan.next(); System.out.println("Enter the firstname"); String firstName =
			 * scan.next(); System.out.println("Enter the address"); String address =
			 * scan.next(); System.out.println("Enter the city"); String city = scan.next();
			 * 
			 * int result = stmt.executeUpdate("insert into persons values(" + id +",'" +
			 * lastName +"','" + firstName +"','" + address + "','" + city + "')");
			 * if(result == 0) { System.out.println("Records are not inserted"); } else {
			 * System.out.println("Records are inserted"); }
			 */
			
			//updating records
			/*
			 * int count =
			 * stmt.executeUpdate("update persons set firstname='Nivi' where personid=2");
			 * System.out.println(count + " Record(s) updated.");
			 */
			
			//Deleting Records
			/*
			 * scan = new Scanner(System.in); System.out.println("Enter the id"); int id =
			 * scan.nextInt(); String sql = "delete from persons where personid='" + id +
			 * "'"; int result = stmt.executeUpdate(sql); // conn.commit(); if (result == 0)
			 * { System.out.println("record not found to delete"); } else {
			 * System.out.println(result+" no.of record(s) found and deleted"); }
			 */
			
			
			
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
				if (stmt != null)
					stmt.close();
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


