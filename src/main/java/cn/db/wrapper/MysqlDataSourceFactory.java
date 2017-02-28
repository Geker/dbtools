package cn.db.wrapper;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

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
public enum MysqlDataSourceFactory {
	INSTANCE;

    private MysqlDataSource dataSource;

	private MysqlDataSourceFactory() {
		initConn();

	}

	private void initConn() {
		try {
            Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        MysqlDataSource opds = new MysqlDataSource();
        opds.setURL("jdbc:mysql://localhost:3306/db_lotest");
        opds.setUser("root");
        opds.setPassword("root");
        dataSource = opds;
	}

	public DataSource getDataSource() {

		return dataSource;
	}
}
