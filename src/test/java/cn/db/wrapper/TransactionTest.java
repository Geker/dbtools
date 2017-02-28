package cn.db.wrapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import cn.db.bean.Dept;
import cn.db.svc.DeptSvc;
import cn.spring.config.SpringConfig;

/**
 * <p>
 * Title: TransactionTest
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: ufo Copyright (C) 2017
 * </p>
 *
 * @author SONGQQ
 * @version
 * @since 2017年2月28日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class TransactionTest {

    /*************
     * 本测试表明，使用TransactionTemplate时,其内部会创建事务。
     * 1.其事务实际开始为第一条修改的语句；
     * 2.其事务的隔离级别和db的隔离级别一直（？设置存疑？）
     * 3.其不同事务之间的关系和db的关系一直
     * 4.如果对相同的row的修改，导致事务锁定，后续的事务会被挂起，等待前面的事务执行完毕；
     * 5.time_out超时时间会让超时的事务失败
     * 
     ****************/
    @Autowired
    TransactionTemplate template;
    @Autowired
    DeptSvc deptSvc;

    @Test
    public void test() {
        template.execute(new TransactionCallback<Dept>() {

            @Override
            public Dept doInTransaction(TransactionStatus status) {
                System.err.println("begin  test");
                deptSvc.showAll();
                deptSvc.showOneRow(20);
                deptSvc.modifyOneRow(20, "tianjin");
                deptSvc.showOneRow(20);
                deptSvc.showOneRow(10);

                return null;
            }
        });
    }

    @Test
    public void test2() {
        template.execute(new TransactionCallback<Dept>() {

            @Override
            public Dept doInTransaction(TransactionStatus status) {
                System.err.println("begin  test2");
                deptSvc.showOneRow(10);
                deptSvc.showOneRow(20);
                deptSvc.modifyOneRow(10, "nanjing");
                deptSvc.showOneRow(20);
                deptSvc.showOneRow(10);
                return null;
            }
        });
    }

    @Test
    public void test3() {
        template.execute(new TransactionCallback<Dept>() {

            @Override
            public Dept doInTransaction(TransactionStatus status) {

                deptSvc.modifyOneRow(10, "chaoyang");
                return null;
            }
        });
    }

}
