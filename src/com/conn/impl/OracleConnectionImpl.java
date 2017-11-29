package com.conn.impl;

import com.conn.test.ConnectionTest;

public class OracleConnectionImpl implements ConnectionTest {

	

	@Override
	public void connectiondb() {
		System.out.println("Oracle -connectiondb");
		
	}

	@Override
	public void connectionDriver() {

		System.out.println("Oracle -connectionDriver");
	}

	@Override
	public void connectionStatement() {

		System.out.println("Oracle -connectionStatement");
	}

}
