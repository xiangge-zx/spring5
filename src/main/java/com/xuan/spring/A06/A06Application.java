package com.xuan.spring.A06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;


/**
 *  Aware 接口及InitialzingBean 接口
 */
public class A06Application {

    private static final Logger log = LoggerFactory.getLogger(A06Application.class);

    public static void main(String[] args) {
        /**
         *  Aware 接口用于注入一些与容器相关信息，例如
         *      a.BeanNameAware 注bean的名字
         *      b.BeanFactoryAward 注入 BeanFactory容器
         *      c.ApplicationContextAware 注入 ApplicationContext 容器
         *      d.EmbeddedValueResolverAware ${}
         */
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("myBean",MyBean.class);
        context.refresh();
        context.close();
        /*
         Aware是spring最原始的注入注解 一般情况下不会失效
         失效的情况有 配置类 比后处理器先注册 导致配置类里面的@注解没有被扫描到 这个时候实现原生的Aware就可以都被扫描
        * */


    }



}
