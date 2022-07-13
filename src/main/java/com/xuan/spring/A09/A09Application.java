package com.xuan.spring.A09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 *  初始化和销毁的执行顺序
 */
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class A09Application {

    public static final Logger log = LoggerFactory.getLogger(A09Application.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(A09Application.class);
        // 由于配置是多例 但是依赖注入只发生一次
        E e = context.getBean(E.class);
        log.debug("{}",e.getF1().getClass());
        log.debug("{}",e.getF1());
        log.debug("{}",e.getF1());
        log.debug("{}",e.getF1());

        context.close();
    }




}
