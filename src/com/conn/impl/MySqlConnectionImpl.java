package com.conn.impl;

import com.conn.test.ConnectionTest;

public class MySqlConnectionImpl implements ConnectionTest {

	

	@Override
	public void connectiondb() {
		System.out.println("MySql -connectiondb");
		
	}

	@Override
	public void connectionDriver() {

		System.out.println("MySql -connectionDriver");
	}

	@Override
	public void connectionStatement() {

		System.out.println("MySql -connectionStatement");
	}

}
