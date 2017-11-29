package com.conn.navigate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NavigateExample

{

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/test";

	// Database credentials

	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

		try {

			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			System.out.println("Creating statement...");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			String sql;

			sql = "SELECT emp_id, firstName, lastName, emp_age FROM Employees";

			ResultSet rs = stmt.executeQuery(sql);

			// Move cursor to the last row.
			System.out.println("Moving cursor to the last...");
			rs.last();

			// STEP 5: Extract data from result set

			System.out.println("Displaying record...");
			// Retrieve by column name
			int id = rs.getInt("emp_id");

			int age = rs.getInt("emp_age");

			String first = rs.getString("firstName");

			String last = rs.getString("lastName");

			// Display values

			System.out.print("ID: " + id);

			System.out.print(", Age: " + age);

			System.out.print(", First: " + first);

			System.out.println(", Last: " + last);

			// Move cursor to the first row.
			System.out.println("Moving cursor to the first row...");
			rs.first();

			// STEP 6: Extract data from result set
			System.out.println("Displaying record...");
			// Retrieve by column

			id = rs.getInt("emp_id");

			age = rs.getInt("emp_age");

			first = rs.getString("firstName");

			last = rs.getString("lastName");

			// Display values

			System.out.print("ID: " + id);

			System.out.print(", Age: " + age);

			System.out.print(", First: " + first);

			System.out.println(", Last: " + last);

			// Move cursor to the first row.

			System.out.println("Moving cursor to the next row...");
			
			while(rs.next()) {

			// STEP 7: Extract data from result set
			System.out.println("Displaying record...");
			id = rs.getInt("emp_id");
			age = rs.getInt("emp_age");

			first = rs.getString("firstName");

			last = rs.getString("lastName");

			// Display values

			System.out.print("ID: " + id);

			System.out.print(", Age: " + age);

			System.out.print(", First: " + first);

			System.out.println(", Last: " + last);

			// STEP 8: Clean-up environment

			}
			rs.close();

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

		}

	}// end try

}
