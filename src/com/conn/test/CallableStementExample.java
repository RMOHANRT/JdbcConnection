package com.conn.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CallableStementExample {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/test";

	// Database credentials

	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement stmt = null;
		try {

			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection S
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query 
			System.out.println("Creating statement...");

			//CALL `test`.`testByRam`(<{IN Id int}>, <{out firstName VARCHAR(255)}>, <{out lastName VARCHAR(255)}>, <{out age int}>);
			
			//String sql = "{ call test.testByRam (?,?,?,?) }";
			
			String sql = "{call new_procedure(?)}";
			stmt = conn.prepareCall(sql);

			stmt.registerOutParameter(1, java.sql.Types.DATE);
			// Bind IN parameter first, then bind OUT parameter
			/*int empID = 104;

			stmt.setInt(1, empID); // This would set ID as 102

			// Because second parameter is OUT so register it
			stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(4, java.sql.Types.INTEGER);
*/
			// Use execute method to run stored procedure.
			System.out.println("Executing stored procedure...");
			stmt.execute();

			// Retrieve employee name with getXXX method
			
			String date = stmt.getString(1);
			/*String firstName = stmt.getString(2);
			String lastName = stmt.getString(3);
			int age = stmt.getInt(4);
*/
//			System.out.println("Emp ID:" + empID + " firstName " + firstName + " lastName :" + lastName + " age" + age);

			System.out.println("date:"+date);
			stmt.close();

			conn.close();

		} catch (SQLException se) {

			// Handle errors for JDBC

			se.printStackTrace();

		} catch (Exception e) {

			// Handle errors for Class.forName

			e.printStackTrace();

		} finally {

			// finally block used to close resources try{

			try {

				if (conn != null)

					conn.close();
				if (stmt != null)

					stmt.close();

			} catch (SQLException se) {

				se.printStackTrace();

			} // end finally try

		} // end try

		System.out.println("Goodbye!");

	}// end main

	// end JDBCExample

}
