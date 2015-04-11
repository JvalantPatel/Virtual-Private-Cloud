package com.application.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/private_cloud_283";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";
	
	public Connection connection = null;
	private static DBConnection instance;
	
	private DBConnection() {	
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static DBConnection getInstance() {
		if(instance == null)
			instance = new DBConnection();
		
		return instance;
	}

}
