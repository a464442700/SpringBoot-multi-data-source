package com.lxf.migration.config;

import com.lxf.migration.mapper.BFSMapper;
import com.lxf.migration.service.BFS;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.lxf.migration.mapper", sqlSessionTemplateRef = "localSqlSessionTemplate")
public class LocalDBConfig {

    // 创建数据源
    @Bean(name = "localDataSource")
    public DataSource localDataSource() {
        //DruidDataSource dataSource = new DruidDataSource();
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@47.99.191.156:1521:DUPDB");
        dataSource.setUsername("appsquery");
        dataSource.setPassword("appsquery");
        return dataSource;
    }

//    @ConfigurationProperties(prefix = "spring.datasource.local")
//    @Primary
//    public DataSource localDataSource() {
//        return DataSourceBuilder.create().build();
//    }

    // 创建SqlSessionFactory
    @Bean(name = "localSqlSessionFactory")
    @Primary
    public SqlSessionFactory localSqlSessionFactory(@Qualifier("localDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    // 创建SqlSessionTemplate
    @Bean(name = "localSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate localSqlSessionTemplate(@Qualifier("localSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        System.out.println("sqlSessionFactory"+sqlSessionFactory);
        sqlSessionFactory.getConfiguration().addMapper(BFSMapper.class);
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "mapper")
    @Primary
    public BFSMapper mapper(@Qualifier("localSqlSessionTemplate") SqlSessionTemplate localSqlSessionTemplate) throws Exception {
     System.out.println(localSqlSessionTemplate);
        return  localSqlSessionTemplate.getMapper(BFSMapper.class);
    }
//    @Bean
//    public BFSMapper mapper(SqlSessionTemplate localSqlSessionTemplate) {
//        System.out.println("localSqlSessionTemplate"+localSqlSessionTemplate);
//       return localSqlSessionTemplate.getMapper(BFSMapper.class);
//    }

//    @Bean(name = "mapper")
//    public MapperFactoryBean<BFSMapper> mapper(@Qualifier("localSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        MapperFactoryBean<BFSMapper> factoryBean = new MapperFactoryBean<BFSMapper>(BFSMapper.class);
//        factoryBean.setSqlSessionFactory(sqlSessionFactory);
//    System.out.println(factoryBean);
//        return factoryBean;
//    }
}
