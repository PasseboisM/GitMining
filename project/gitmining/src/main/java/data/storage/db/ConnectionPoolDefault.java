package data.storage.db;

import data.storage.db.service.ConnectionPool;

public class ConnectionPoolDefault implements ConnectionPool {
	
	private static ConnectionPool instance = new ConnectionPoolDefault();
	
	public ConnectionPoolDefault() {
		// TODO Auto-generated constructor stub
	}

	public static ConnectionPool getInstance() {
		return instance;
	}
}
