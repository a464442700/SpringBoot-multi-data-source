package com.lxf.migration.config;

import com.lxf.migration.mapper.BFSMapper;
import com.lxf.migration.pojo.DbaObjects;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;






@Configuration
@MapperScan("com.lxf.migration.mapper")
public class DataSourceConfig {
    @Autowired
    private org.springframework.core.env.Environment env;


    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        String driver = env.getProperty("spring.datasource.driver-class-name");
        String userName = env.getProperty("spring.datasource.username");
        String passWord = env.getProperty("spring.datasource.password");
        String url = env.getProperty("spring.datasource.url");

        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver(driver);
        dataSource.setUsername(userName);
        dataSource.setPassword(passWord);
        dataSource.setUrl(url);
        dataSource.setDefaultAutoCommit(false);
//采用MyBatis的JDBC事务方式
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        org.apache.ibatis.mapping.Environment environment = new org.apache.ibatis.mapping.Environment("development", transactionFactory, dataSource);
//创建Configuration对象
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(environment);//注册一个MyBatis上下文别名

//使用SqlSessionFactoryBuilder构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(configuration);


        return sqlSessionFactory;

    }

    @Bean
    public  SqlSession sqlSession(SqlSessionFactory sqlSessionFactory){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
    @Bean
    public BFSMapper mapper(SqlSession sqlSession) {


        BFSMapper mapper = (BFSMapper) sqlSession.getMapper(BFSMapper.class);
        return mapper;
    }

}