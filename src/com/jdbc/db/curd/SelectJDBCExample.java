package com.jdbc.db.curd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SelectJDBCExample {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/students";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {

		Connection conn = null;
		try {

			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection

			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");
		} catch (SQLException se) {

			// Handle errors for JDBC

			se.printStackTrace();

		} catch (Exception e) {

			// Handle errors for Class.forName

			e.printStackTrace();

		} finally {

			// finally block used to close resources
			try {

				if (conn != null)

					conn.close();

			} catch (SQLException se) {

				se.printStackTrace();

			} // end finally try

		} // end try

		System.out.println("Goodbye!");

	}// end main

}
