package com.xuan.spring.A08;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class MyController { // Controller 是单例

    @Lazy  // 单例使用多例都会发生失效的问题 需要加上@Lazy+
    @Autowired
    private BeanForRequest beanForRequest;

    @Lazy
    @Autowired
    private BeanForSession beanForSession;

    @Lazy
    @Autowired
    private BeanForApplication beanForApplication;

    @GetMapping(value = "/test",produces = "text/html")
    public  String test(HttpServletRequest request, HttpSession session){
        ServletContext sc  = request.getServletContext();
        String sb = "<ul>"+
                    "<li>"+"request scope:"+beanForRequest+"</li>"+
                "<li>"+"request scope:"+beanForSession+"</li>"+
                "<li>"+"request scope:"+beanForApplication+"</li>"+
                "</ul>";
        return sb;
    }


}




