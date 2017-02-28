package cn.db.wrapper;

import java.sql.SQLException;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

/**
 * 
 * <p>
 * Title: DataSourceFactory
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: ufo Copyright (C) 2016
 * </p>
 *
 * @author SONGQQ
 * @version
 * @since 2016年12月2日
 */
public enum DataSourceFactory {
    INSTANCE;

    private final String ip = "localhost";
	private OracleConnectionPoolDataSource dataSource;

	private DataSourceFactory() {
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
			opds.setURL("jdbc:oracle:thin:@" + ip + ":" + 1521 + ":" + "ORCL");
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
