package com.xuan.spring.A20;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan // 如果不是 @ComponentScan("包") 则代表扫描的是该类路径下的包
@PropertySource("classpath:application.properties")  // 把指定配置加载进来
@EnableConfigurationProperties({WebMvcProperties.class, ServerProperties.class})  // 扫描固定前缀 键值绑定到固定对象中
public class WebConfig {
    // 内嵌Web容器工厂
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(ServerProperties serverProperties){
        return  new TomcatServletWebServerFactory(serverProperties.getPort());
    }

    // 创建DispatcherServlet , 真正运行还是在tomcat里面运行
    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    } // tomcat容器首次使用的时候才会被初始化

    // 注册DispatcherServlet ,springMVC 的入口
    @Bean
    public DispatcherServletRegistrationBean dispatcherServletRegistrationBean
    (DispatcherServlet dispatcherServlet,WebMvcProperties webMvcProperties){
        /*所有请求都会到dispatchServlet上 最后由它来分发对应的处理器*/
        DispatcherServletRegistrationBean registrationBean =   new DispatcherServletRegistrationBean(dispatcherServlet,"/");  // "/"的含义 请求过来如果没有和其他路径匹配 那么最终都会和/匹配
        registrationBean.setLoadOnStartup(webMvcProperties.getServlet().getLoadOnStartup()); // 数字低优先级高
        return    registrationBean;
    }

}
