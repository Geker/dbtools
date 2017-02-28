package cn.spring.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import cn.db.wrapper.DataSourceFactory;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "cn")
public class SpringConfig {

    // @Bean
    // public FooRepository fooRepository() {
    // // configure and return a class having @Transactional methods
    // return new JdbcFooRepository(dataSource());
    // }

    @Bean
    public DataSource dataSource() {
        return DataSourceFactory.INSTANCE.getDataSource();
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public TransactionTemplate txTemplate() {
        TransactionTemplate template = new TransactionTemplate(txManager());
        template.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        // template.setTimeout(330);
        return template;
    }

}
