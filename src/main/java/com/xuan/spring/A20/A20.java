package com.xuan.spring.A20;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

public class A20 {
    private  static final Logger log = LoggerFactory.getLogger(A20.class);

    /*
    * DispatcherServlet 是springmcv程序的入口点
    *
    * */
    public static void main(String[] args) {
        /*
         AnnotationConfig 表示支持配置类
         ServletWebServer 表示支持内嵌web容器（包括tomcat容器）
        * */
        AnnotationConfigServletWebServerApplicationContext context =    // 内嵌tomcat容器的一个spring实现
                new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);
    }
}
