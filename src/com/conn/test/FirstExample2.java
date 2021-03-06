package com.conn.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FirstExample2 {

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


			//STEP 4: Execute a query 
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			String sql = "UPDATE Employees set emp_age=30 WHERE emp_id=102";
			//String sql = "SELECT emp_id, firstName, lastName, emp_age FROM Employees";


//				Let us check if it returns a true Result Set or not.
			Boolean ret = stmt.execute(sql); 
			System.out.println("Return value is : " + ret.toString() );

			sql = "UPDATE Employees set emp_age=20 WHERE emp_id=104";

//				Let us update age of the record with ID = 103;

			int rows = stmt.executeUpdate(sql); 
			System.out.println("Rows impacted : " + rows );


//				Let us select all the records and display them. 
			sql = "SELECT emp_id, firstName, lastName, emp_age FROM Employees"; 
			ResultSet rs = stmt.executeQuery(sql);


			//STEP 5: Extract data from result set 
			while(rs.next()){

			//Retrieve by column name

			int id	= rs.getInt("emp_id");

			int age = rs.getInt("emp_age");

			String first = rs.getString("firstName");

			String last = rs.getString("lastName");
			//Display values

			System.out.print("ID: " + id);

			System.out.print(", Age: " + age);

			System.out.print(", First: " + first);

			System.out.println(", Last: " + last);

			}

			//STEP 6: Clean-up environment

			rs.close();

			stmt.close();

			conn.close();

			}catch(SQLException se){

			//Handle errors for JDBC

			se.printStackTrace();

			}catch(Exception e){

			//Handle errors for Class.forName

			e.printStackTrace();

			}finally{

			//finally block used to close resources try{

				try{

					if(stmt!=null)

						stmt.close();

					if(conn!=null)

					conn.close();

					}catch(SQLException se){

					se.printStackTrace();

					}//end finally try


			} 
		
			
			}//end try

			

			}//end main
