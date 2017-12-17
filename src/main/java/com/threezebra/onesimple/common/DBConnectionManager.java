package com.threezebra.onesimple.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionManager {
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onesimpledb", "root", "root");
		}catch (Exception e) {
			// TODO: handle exception
		}
		return conn;
	}
	 public static void main(String[] args) {
		System.out.println("connection:::::"+getConnection());
	  }
		
	

}
