package com.xuan.spring.A05;

import com.alibaba.druid.pool.DruidDataSource;
import com.xuan.spring.A05.compoent.Bean2;
import com.xuan.spring.A05.mapper.Mapper1;
import com.xuan.spring.A05.mapper.Mapper2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@ComponentScan("com.xuan.spring.A05.compoent")
public class Config {

    public Bean2 bean2(){
        return new Bean2();
    }

    @Bean
    public Bean1 bean1(){return  new Bean1();}

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return  sqlSessionFactoryBean;
    }

    @Bean(initMethod = "init")
    public DruidDataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

//    @Bean
//    public MapperFactoryBean<Mapper1> mapper1(SqlSessionFactory sqlSessionFactory){ // 通过工厂来注入
//        MapperFactoryBean<Mapper1> factory =  new MapperFactoryBean<>(Mapper1.class); // mapper1.class 根据什么接口类型 来生产接口类型
//        // 要用到sqlSionFactory工厂 , 才能与数据连接做增删改查
//        factory.setSqlSessionFactory(sqlSessionFactory);
//        return factory;
//    }
//
//    @Bean
//    public MapperFactoryBean<Mapper2> mapper2(SqlSessionFactory sqlSessionFactory){ // 通过工厂来注入
//        MapperFactoryBean<Mapper2> factory =  new MapperFactoryBean<>(Mapper2.class); // mapper1.class 根据什么接口类型 来生产接口类型
//        // 要用到sqlSionFactory工厂 , 才能与数据连接做增删改查
//        factory.setSqlSessionFactory(sqlSessionFactory);
//        return factory;
//    }

}
