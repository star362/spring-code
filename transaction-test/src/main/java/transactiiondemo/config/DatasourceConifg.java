package transactiiondemo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wangyu
 * @date: 2020-05-07 12:07
 *
 * <p>
 */
@Configuration
public class DatasourceConifg {

    @Value("${spring.datasource.driver-class-name}")
    String driverClass;
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String passWord;

    final Logger log = LoggerFactory.getLogger(DatasourceConifg.class);

    @Primary
    @Bean("dataSource1")
    public DataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setPassword(passWord);
        hikariDataSource.setUsername(userName);
        hikariDataSource.setDriverClassName(driverClass);
        return hikariDataSource;
    }

    @Primary
    @Bean("platformTransactionManager1")
    public PlatformTransactionManager platformTransactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

    @Primary
    @Bean("transactionTemplate1")
    public  TransactionTemplate transactionTemplate(){
        return new TransactionTemplate(platformTransactionManager());
    }

    @Primary
    @Bean(name = "sqlSessionFactory1")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource1") DataSource datasource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setTypeAliasesPackage("transactiiondemo");
        final org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        bean.setConfiguration(configuration);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));// 设置mybatis的xml所在位置
        return bean.getObject();
    }


    @Primary
    @Bean(name = "sqlSessionTemplate1")
    public SqlSessionTemplate popSqlSessionTemplate(@Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    @Primary
    @Bean("jdbcTemplate1")
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }







}
