package com.conn.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Insert_EMP {

	public static void main(String[] args) throws Exception {
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
		  Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
		  CallableStatement calstat=conn.prepareCall("{call empproc(?,?,?,?)}");
		  calstat.setString(1,"889");
		  calstat.setString(2,"40");
		  calstat.setString(3,"venkat");
		  calstat.setString(4,"T");
		  calstat.executeQuery();
		  
		  conn.close();
		  calstat.close();
		  System.out.println("Your data has been inserted into table.");
		  }
		}
