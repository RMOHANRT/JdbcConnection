package com.conn.impl;

import com.conn.test.ConnectionTest;

public class Db2ConnectionImpl implements ConnectionTest {

	

	@Override
	public void connectiondb() {
		System.out.println("db2 -connectiondb");
		
	}

	@Override
	public void connectionDriver() {

		System.out.println("db2 -connectionDriver");
	}

	@Override
	public void connectionStatement() {

		System.out.println("db2 -connectionStatement");
	}

}
