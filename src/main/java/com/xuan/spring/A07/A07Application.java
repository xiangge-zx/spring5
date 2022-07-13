package com.xuan.spring.A07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


/**
 *  初始化和销毁的执行顺序
 */
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class) // 没有添加数据库配置
public class A07Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(A07Application.class,args);
        context.close();
        /*
        *  a.spring提供了多种初始化和销毁的手段
        *
        *
        * */
    }

    @Bean(initMethod="init3")
    public Bean1 bean1(){
        return new Bean1();
    }

    @Bean(destroyMethod = "destroy3")
    public Bean2 bean2(){
        return new Bean2();
    }


}
