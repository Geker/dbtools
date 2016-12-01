package cn.ffcs.memory;

import java.sql.SQLException;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public enum MemoryFactory {
	INSTANCE;

	private OracleConnectionPoolDataSource dataSource;

	private MemoryFactory() {
		initConn();

	}

	private void initConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			OracleConnectionPoolDataSource opds = new OracleConnectionPoolDataSource();
			opds.setURL("jdbc:oracle:thin:@" + "192.168.11.114" + ":" + 1521 + ":" + "ORCL");
			opds.setUser("scott");
			opds.setPassword("tiger");
			dataSource = opds;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public DataSource getDataSource() {

		return dataSource;
	}
}
