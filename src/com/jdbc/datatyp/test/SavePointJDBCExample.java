package com.jdbc.datatyp.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class SavePointJDBCExample {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/test";
	static final String USER = "root";
	static final String PASS = "root";
	
	public static void main(String[] args) throws Exception {
	
		Connection conn = null;

		Statement stmt = null;
		
		try {
		//STEP 2: Register JDBC driver 
		Class.forName("com.mysql.jdbc.Driver");


		//STEP 3: Open a connection 
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(DB_URL,USER,PASS);

		//STEP 4: Set auto commit as false.

		conn.setAutoCommit(false);


		//STEP 5: Execute a query to delete statment with

//			required arguments for RS example. 
		System.out.println("Creating statement..."); 
		stmt = conn.createStatement();
		
		String sql = "SELECT emp_id, firstName, lastName, emp_age FROM Employees"; 
		ResultSet rs = stmt.executeQuery(sql); 
		System.out.println("List result set for reference...."); 
		printRs(rs);


//		STEP 7: delete rows having ID grater than 104

//		But save point before doing so.

	Savepoint savepoint1 = conn.setSavepoint("ROWS_DELETED_1");
	System.out.println("Deleting row....");

	String SQL = "DELETE FROM Employees " + "WHERE emp_id = 102";

	stmt.executeUpdate(SQL);

	// oops... we deleted too wrong employees!

	//STEP 8: Rollback the changes afetr save point 2.

	String sql2 = "SELECT emp_id, firstName, lastName, emp_age FROM Employees"; 
	ResultSet rs2 = stmt.executeQuery(sql2); 
	System.out.println("List result set for reference...."); 
	printRs(rs2);
	
	
	conn.rollback(savepoint1);
	
	
	System.out.println("-----------------after savepoint1 ----------------------");
	
	String sql1 = "SELECT emp_id, firstName, lastName, emp_age FROM Employees"; 
	ResultSet rs1 = stmt.executeQuery(sql1); 
	System.out.println("List result set for reference...."); 
	printRs(rs1);
	
	


	// STEP 9: delete rows having ID grater than 104 // But save point before doing so.

	Savepoint savepoint2 = conn.setSavepoint("ROWS_DELETED_2"); 
	System.out.println("Deleting row...."); 
	
	SQL = "DELETE FROM Employees " +"WHERE emp_id = 206";

	stmt.executeUpdate(SQL);


	//STEP 10: Now list all the available records.

	sql = "SELECT emp_id, firstName, lastName, emp_age FROM Employees";

	rs = stmt.executeQuery(sql);

	System.out.println("List result set for reference....");

	printRs(rs);


	//STEP 10: Clean-up environment

	rs.close();

	stmt.close();

	conn.close();


	} catch(SQLException se) {

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