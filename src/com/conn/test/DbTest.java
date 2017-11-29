package com.conn.test;

import com.conn.impl.PostGreSqlConnectionImpl;

public class DbTest {

	public static void main(String[] args) {
 
		ConnectionTest ct = new PostGreSqlConnectionImpl();
		
		ct.connectiondb();
		ct.connectionDriver();
		ct.connectionStatement();
	}

}
