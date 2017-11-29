package com.jdbc.datatyp.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchJDBCExample {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/test";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Connecting to database...");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Create statement
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			
			printRows(stmt); // alt + <- 
			
			
			// Set auto-commit to false
			conn.setAutoCommit(false);

			// Create SQL statement

			String SQL = "INSERT INTO Employees (emp_id, firstName, lastName, emp_age) " + "VALUES(309,'gowtham', 't', 10)";

			// Add above SQL statement in the batch.
			stmt.addBatch(SQL);

			// Create one more SQL statement

			SQL = "INSERT INTO Employees (emp_id, firstName, lastName, emp_age) " + "VALUES(310,'DheeRaj', 'T', 12)";

			// Add above SQL statement in the batch. 
			stmt.addBatch(SQL);

			// Create one more SQL statement

			SQL = "UPDATE Employees SET emp_age = 35 " + "WHERE emp_id = 108";

			// Add above SQL statement in the batch.
			stmt.addBatch(SQL);

			// Create an int[] to hold returned values
			int[] count = stmt.executeBatch();
			// Explicitly commit statements to apply changes
			conn.commit();

			// Again, let us select all the records and display them.
			printRows(stmt);

			// Clean-up environment

			stmt.close();

			conn.close();

		} catch (SQLException se) {

			System.out.println("Rolling back data here....");

			try {

				if (conn != null)

					conn.rollback();
			} catch (SQLException se2) {

				se2.printStackTrace();
			} // end try

		} catch (Exception e) {

			// Handle errors for Class.forName

			e.printStackTrace();

		} finally {

			// finally block used to close resources try{

			try {

				if (stmt != null)

					stmt.close();

				if (conn != null)

					conn.close();

			} catch (SQLException se) {

				se.printStackTrace();

			} // end finally try

		}

	}

	public static void printRows(Statement stmt) throws SQLException {

		System.out.println("Displaying available rows...");

		// Let us select all the records and display them.

		String sql = "SELECT emp_id, firstName, lastName, emp_age FROM Employees";

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {

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

		}

		System.out.println();

		rs.close();

	}// end printRows()

}// end JDBCExample
