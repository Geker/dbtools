package cn.db.svc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.google.common.base.Verify;

import cn.db.bean.Dept;

@Component
public class DeptSvc {

    @Autowired
    DataSource ds;

    private final String sql = "select * from dept";
    private final String sql_deptNo = "select * from dept where deptNo=?";
    private final String sql_mod = "update dept set loc=? where deptNo=?";

    public void showAll()
    {
        JdbcTemplate jt = new JdbcTemplate(ds);
        RowMapper<Dept> rowMapper = new BeanPropertyRowMapper<>(Dept.class);
        List<Dept> xx = jt.query(sql, rowMapper);
        System.err.println(xx);
    }

    public void showOneRow(int deptNo) {
        JdbcTemplate jt = new JdbcTemplate(ds);
         RowMapper<Dept> rowMapper = new BeanPropertyRowMapper<>(Dept.class);
        Dept dept = jt.queryForObject(sql_deptNo, rowMapper, deptNo);
        // List<Dept> xx = jt.query(sql, rowMapper);
        
        System.err.println(dept);
    }

    public void modifyOneRow(int deptNo, String loc) {
        JdbcTemplate jt = new JdbcTemplate(ds);
        RowMapper<Dept> rowMapper = new BeanPropertyRowMapper<>(Dept.class);
        int i = jt.update(sql_mod, loc, deptNo);
        Verify.verify(i == 1);
        // List<Dept> xx = jt.query(sql, rowMapper);
    }

}
