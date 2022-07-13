package com.xuan.spring.A08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


/**
 *  scope的5种类型
 *  singleton:获取bean的时候 每次返回的都是同一个对象
 *  prototype:每次获取bean的时候，都会产生新的对象
 *  request:这个bean存在于request的生命周期当中，请求结束bean的生命周期也随之结束
 *  session:同一个回话开始bean会创建，回话结束bean就销毁
 *  application:应用程序启动时bean被创建，应用程序（web里面的servletContext）销毁时bean被销毁
 *
 *
 *  如果jdk>8 ，运行时请添加 --add-opens java.base/java.lang=ALL-UNNAMED
 */
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class A08Application {
    public static void main(String[] args) {
        SpringApplication.run(A08Application.class,args);

        /*
        *  a.spring提供了多种初始化和销毁的手段
        *
        *
        * */
    }


}
