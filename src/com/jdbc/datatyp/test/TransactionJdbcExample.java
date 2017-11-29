package com.jdbc.datatyp.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionJdbcExample {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/test";
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

			// STEP 4: Set auto commit as false.

			conn.setAutoCommit(false);

			// STEP 5: Execute a query to create statment with

			// required arguments for RS example.
			System.out.println("Creating statement...");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			// STEP 6: INSERT a row into Employees table
			System.out.println("Inserting one row....");
			String SQL = "INSERT INTO Employees " + "VALUES (136, 20, 'Rita', 'Tez')";

			stmt.executeUpdate(SQL);

			// STEP 7: INSERT one more row into Employees table
			SQL = "INSERT INTO Employees " + "VALUES (107, 22, 'Sita', 'Singh')";
			stmt.executeUpdate(SQL);

			// STEP 8: Commit data here. System.out.println("Commiting data here....");
			conn.commit();

			// STEP 9: Now list all the available records.

			String sql = "SELECT emp_id, firstName, lastName, emp_age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("List result set for reference....");
			printRs(rs);

			// STEP 10: Clean-up environment

			rs.close();

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

	public static void printRs(ResultSet rs) throws SQLException { // Ensure we start with first row

		rs.beforeFirst();

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

	}// end printRs()

}
