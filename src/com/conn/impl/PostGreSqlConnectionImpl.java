package com.conn.impl;

import com.conn.test.ConnectionTest;

public class PostGreSqlConnectionImpl implements ConnectionTest {

	

	@Override
	public void connectiondb() {
		System.out.println("PostGreSql -connectiondb");
		
	}

	@Override
	public void connectionDriver() {

		System.out.println("PostGreSql -connectionDriver");
	}

	@Override
	public void connectionStatement() {

		System.out.println("PostGreSql -connectionStatement");
	}

}
