package com.conn.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PreparedStatementExample {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/test";

	// Database credentials

	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null; 

		try {

			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "UPDATE Employees set emp_age=? WHERE emp_id=?"; 
			pstmt = conn.prepareStatement(sql);


			//Bind values into the parameters. 
			pstmt.setInt(1, 28); // This would set age 
			pstmt.setInt(2, 103); // This would set ID


//				Let us update age of the record with ID = 102; 
			int rows = pstmt.executeUpdate(); 
			System.out.println("Rows impacted : " + rows );


//				Let us select all the records and display them. 
			sql = "SELECT emp_id, firstName, lastName, emp_age FROM Employees"; 
			
			ResultSet rs = pstmt.executeQuery(sql);


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

			pstmt.close();


			 




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

					if(pstmt!=null)

						pstmt.close();

					if(conn!=null)

					conn.close();

					}catch(SQLException se){

					se.printStackTrace();

					}//end finally try


			} 
		
			
			}//end try

			

			}//end main
