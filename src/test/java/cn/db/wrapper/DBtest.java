package cn.db.wrapper;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import cn.db.bean.Dept;

public class DBtest {

    private static final Logger logger = LoggerFactory.getLogger(DBtest.class);
    private static final String sql = "select * from dept";
	DataSource ds;

	@Before
	public void testbefore() {
		ds = DataSourceFactory.INSTANCE.getDataSource();

	}

	@Test
	public void test() {
		// DataSource ds = MemoryFactory.INSTANCE.getDataSource();
		JdbcTemplate jt = new JdbcTemplate(ds);

		RowMapper<Dept> rowMapper = new BeanPropertyRowMapper<>(Dept.class);
		List<Dept> xx = jt.query(sql, rowMapper);
		System.out.println(xx.get(0));
        logger.debug(xx.get(0).toString());
	}

	@Test
	public void testDbutils() throws SQLException {
		QueryRunner run = new QueryRunner(ds);
		List<Dept> l = run.query(sql, new BeanListHandler<>(Dept.class));
//		run.insertBatch(sql, rsh, params);
		System.out.println(l.get(0));

	}

}
