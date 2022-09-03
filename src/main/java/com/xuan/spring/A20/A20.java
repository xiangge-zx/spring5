package com.xuan.spring.A20;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class A20 {
    private static final Logger log = LoggerFactory.getLogger(A20.class);

    /*
     * DispatcherServlet 是springmcv程序的入口点
     *
     * spring-boot:2.6.7 spring.factories 里面有默认配置
     * 需要通过手动应用去修改
     *
     * */
    public static void main(String[] args) throws Exception {
        /*
         AnnotationConfig 表示支持配置类
         ServletWebServer 表示支持内嵌web容器（包括tomcat容器）
        * */
        AnnotationConfigServletWebServerApplicationContext context =    // 内嵌tomcat容器的一个spring实现 会默认扫描当前类路径
                new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);

        // 作用 解析@RequestMapping 以及派生注解 生产路径与控制器方法的映射关系 在初始化的时候就生成
        RequestMappingHandlerMapping handlerMapping = context.getBean(RequestMappingHandlerMapping.class);

        // 获取映射结果
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        handlerMethods.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });

        // 请求来了 获取控制器方法 返回处理器执行链对象
//        HandlerExecutionChain chain = handlerMapping.getHandler(new MockHttpServletRequest("GET","/test1"));
//        System.out.println(chain);
        MockHttpServletRequest request = new MockHttpServletRequest("POST","/test2");
        request.setParameter("name","张三");
        MockHttpServletResponse response = new MockHttpServletResponse();
        HandlerExecutionChain chain = handlerMapping.getHandler(request);
        System.out.println(chain);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // 里面有很多其他组件用来解析注解(比如@Param)
        MyRequestMappingHandlerAdapter handlerAdapter = context.getBean(MyRequestMappingHandlerAdapter.class);
        handlerAdapter.invokeHandlerMethod( request,  response, (HandlerMethod)chain.getHandler());


        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 所有的参数解析器");
        for (HandlerMethodArgumentResolver resolver : handlerAdapter.getArgumentResolvers()) {
            System.out.println(resolver);
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 所有的返回值解析器");
        for (HandlerMethodReturnValueHandler handler : handlerAdapter.getReturnValueHandlers()) {
            System.out.println(handler);
        }

    }
}
