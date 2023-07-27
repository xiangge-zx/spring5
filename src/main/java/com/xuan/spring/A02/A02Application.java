package com.xuan.spring.A02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


/**
 *  第二讲 Application的几个实现类
 *
 *
 */
public class A02Application {

    private static final Logger log = LoggerFactory.getLogger(A02Application.class);

    public static void main(String[] args) {
        testClassPathXmlApplicationContext();
//        testFileSystemXmlApplicationContext();
//        testAnnotationConfigApplicationContext();
    }

    private static void testClassPathXmlApplicationContext(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("b01.xml"); // 把文件名传入，固定从类路径下找这个文件
        for (String name:context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        System.out.println(context.getBean(Bean2.class).getBean1());
    }

    private static void testFileSystemXmlApplicationContext(){
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("src\\main\\resources\\b01.xml"); // 把文件名传入
        for (String name:context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        System.out.println(context.getBean(Bean2.class).getBean1());
    }

    // 基于注解@Config配置的方式来实现bean的注入
    private static void testAnnotationConfigApplicationContext(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        for(String name:context.getBeanDefinitionNames()){
            System.out.println(name);
        }

        System.out.println(context.getBean(Bean2.class).getBean1());
    }

    // 较为经典的容器，java配置类来创建，用于web环境
    private static void testAnnotationConfigServletWebServerApplicationContext(){
        // 内嵌一个基于servlet技术的web容器，没有web容器是无法运行在web环境下的 AnnotationConfigServletWebServerApplicationContext 是加了很多后处理器的context
        AnnotationConfigServletWebServerApplicationContext context = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);
    }

    @Configuration
    static  class WebConfig{
        @Bean
        public ServletWebServerFactory servletWebServerFactory(){
            // tomcat服务器的web工厂
            return  new TomcatServletWebServerFactory();
        }

        /**
         * spring web 层面的技术 他的核心在于DispatcherServlet
         *
         * 浏览器 postman 请求的的一个入口点是 dispatcherServlet（前控制器）
         *
         * @return
         */
//        @Bean
//        public DispatcherServlet dispatcherServlet(){
//            return new DispatcherServlet();
//        }

        // DispatcherServlet并不知道要到tomcat里面去
//        @Bean
//        public DispatcherServletRegistrationBean registrationBean(DispatcherServlet dispatcherServlet){
//            return new DispatcherServletRegistrationBean(dispatcherServlet,"/"); // 给dispatcheerServlet一个路径注册到bean容器里面去
//        }

        // @requestmaping @postmaping 可以用来创建控制器
//        @Bean
//        public Controller controller1(){
//            return (request,respones)->{
////                respones.getWi
//            }
//        }

    }

    @Configuration
    static class Config{
        @Bean
        public Bean1 bean1(){
            return new Bean1();
        }

        @Bean
        public Bean2 bean2(Bean1 bean1){
            Bean2 bean2 = new Bean2();
            bean2.setBean1(bean1);
            return new Bean2();
        }
    }

    static class Bean1{

    }

    static class Bean2{

        private Bean1 bean1;

        public Bean1 getBean1() {
            return bean1;
        }

        public void setBean1(Bean1 bean1) {
            this.bean1 = bean1;
        }

    }

}
