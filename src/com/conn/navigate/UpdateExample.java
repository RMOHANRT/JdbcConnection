package com.conn.navigate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateExample

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
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			String sql;

			sql = "SELECT emp_id, firstName, lastName, emp_age FROM Employees";

			ResultSet rs = stmt.executeQuery(sql);

			// STEP 6: Loop through result set and add 5 in age //Move to BFR postion so
			// while-loop works properly rs.beforeFirst();

			// STEP 7: Extract data from result set
			while (rs.next()) {

				// Retrieve by column name

				System.out.println("id\t"+rs.getInt("emp_id")+":"+"FirstName\t"+rs.getString("firstName")+":"+"LastName\t"+rs.getString("lastName")+":"+"Age\t"+rs.getInt("emp_age"));
				int newAge = rs.getInt("emp_age") + 5;
				
				System.out.println("updating the age \t"+newAge);
				

				rs.updateDouble("emp_age", newAge);

				rs.updateRow();

			}

			System.out.println("List result set showing new ages...");

			// Insert a record into the table.

			// Move to insert row and add column data with updateXXX()
			
			
			System.out.println("Moving cursor to the first row...");
			rs.first();

			// STEP 6: Extract data from result set
			System.out.println("Displaying record...");
			// Retrieve by column

			int id = rs.getInt("emp_id");

			int age = rs.getInt("emp_age");

			String first = rs.getString("firstName");

			String last = rs.getString("lastName");

			// Display values

			System.out.print("ID: " + id);

			System.out.print(", Age: " + age);

			System.out.print(", First: " + first);

			System.out.println(", Last: " + last);


			System.out.println("Inserting a new record...");
			
			//rs.first();
			//rs.insertRow();

			rs.moveToInsertRow();

			rs.updateInt("emp_id", 278);

			rs.updateString("firstName", "chenna");

			rs.updateString("lastName", "Krishna");

			rs.updateInt("emp_age", 32);

			// Commit row

			rs.insertRow();

			System.out.println("List result set showing new set...");

			// Delete second record from the table.

			// Set position to second record first rs.absolute( 2 );

			//System.out.println("List the record before deleting...");
			// Retrieve by column name

			/*int id = rs.getInt("emp_id");
			int age = rs.getInt("emp_age");

			String first = rs.getString("firstName");
			String last = rs.getString("lastName");

			// Display values

			System.out.print("ID: " + id);

			System.out.print(", Age: " + age);

			System.out.print(", First: " + first);

			System.out.println(", Last: " + last);*/

			// Delete row

			//rs.deleteRow();

			System.out.println("List result set after deleting one records...");

			// STEP 8: Clean-up environment

			rs.close();

			stmt.close();

			conn.close();
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
