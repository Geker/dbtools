package cn.ffcs.memory;

import com.mysql.jdbc.Connection;

public enum MemoryFactory {
	INSTANCE;

	private final Connection connection;

	private MemoryFactory() {
		connection = null;
	}

	public Connection getDataSource() {
		return connection;
	}
}
