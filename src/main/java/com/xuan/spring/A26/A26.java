package com.xuan.spring.A26;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.annotation.RequestHeaderMapMethodArgumentResolver;
import org.springframework.web.method.annotation.RequestHeaderMethodArgumentResolver;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.*;

public class A26 {
    /*
    *    |ServletInvocableHandlerMetod|                                        ------>   HandlerMethod   1.bean 2.method
    *    |+invokeAndHandle(ServletWebRequest,ModelAndViewContainer)|
    *    集成了以下功能
    *   WebDataBinderFactory 请求参数绑定器，类型转换
    *   ParameterNameDiscoverer  参数名解析器
    *   HandlerMethodArgumentResolverComposite   负责解析参数
    *   HandlerMethodReturnValueHandlerComposite   负责处理返回值
    * */

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("name","张三");

        /* 通过 ServletInvocableHandlerMetod 把这些组件整合在一起 ,并完成控制器方法得调用,如下*/
        ServletInvocableHandlerMethod handlerMethod = new ServletInvocableHandlerMethod(
                new WebConfig.Controller1(), WebConfig.Controller1.class.getMethod("foo", WebConfig.User.class));  // 这就是bean 和 method

        ServletRequestDataBinderFactory  factory = new ServletRequestDataBinderFactory(null,null);

        handlerMethod.setDataBinderFactory(factory);
        handlerMethod.setParameterNameDiscoverer(new DefaultParameterNameDiscoverer());
        handlerMethod.setHandlerMethodArgumentResolvers(getArgumentResolver(context));

        // 在调用中 任何模型对象都会放在ModewlAndViewContainer中
        ModelAndViewContainer container = new ModelAndViewContainer();
        handlerMethod.invokeAndHandle(new ServletWebRequest(request),container);

        System.out.println(container);

        context.close();
    }

    /*参数解析器 加入常用参数解析器*/
    public static HandlerMethodArgumentResolverComposite getArgumentResolver(AnnotationConfigApplicationContext context){
        HandlerMethodArgumentResolverComposite composite = new HandlerMethodArgumentResolverComposite();
        composite.addResolvers(
                new RequestParamMethodArgumentResolver(context.getDefaultListableBeanFactory(),false),
                new PathVariableMethodArgumentResolver(),
                new RequestHeaderMethodArgumentResolver(context.getDefaultListableBeanFactory()),
                new ServletCookieValueMethodArgumentResolver(context.getDefaultListableBeanFactory()),
                new ServletRequestMethodArgumentResolver(),
                new ServletModelAttributeMethodProcessor(false),  // 解析 @ModelAttribute注解 不可以省略
//                new RequestResponseBodyMethodProcessor(List.of(new MappingJackson2HttpMessageConverter())),
                new ServletModelAttributeMethodProcessor(true),  // 解析 @ModelAttribute注解 可以省略
                new RequestParamMethodArgumentResolver(context.getDefaultListableBeanFactory(),true)
        );
        return  composite;
    }

}
