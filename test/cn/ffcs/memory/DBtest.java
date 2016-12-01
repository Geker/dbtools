package cn.ffcs.memory;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class DBtest {
	private static final String sql = "select * from T";
	DataSource ds;

	@Before
	public void testbefore() {
		ds = MemoryFactory.INSTANCE.getDataSource();

	}

	@Test
	public void test() {
		// DataSource ds = MemoryFactory.INSTANCE.getDataSource();
		JdbcTemplate jt = new JdbcTemplate(ds);

		RowMapper<T> rowMapper = new BeanPropertyRowMapper<>(T.class);
		List<T> xx = jt.query(sql, rowMapper);
		System.out.println(xx.get(0));

	}

	@Test
	public void testDbutils() throws SQLException {
		QueryRunner run = new QueryRunner(ds);
		List<T> l = run.query(sql, new BeanListHandler<>(T.class));
//		run.insertBatch(sql, rsh, params);
		System.out.println(l.get(0));

	}

}
