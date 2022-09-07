package com.xuan.spring.A35;

import com.xuan.spring.A26.WebConfig;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

public class A35 {
    public static void main(String[] args) {
        AnnotationConfigServletWebServerApplicationContext context
                = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);

  /*          小结
                a.HandlerMapping 负责建立请求与控制器之间的关系
                    - RequestMappingHandlerMapping (与@RequestMapping 匹配)
                    - WelcomePageHandlerMapping    （/）
                    - BeanNameUrlHandlerMapping     (与bean的名字匹配 以 / 开头)
                    - RouterFunctionMapping         (函数式 RequestPredicate , HandlerFunction)
                    - SimpleUrlHandlerMapping       (静态资源 通配符 /** /img/**)
                    执行也会有顺序 碰到一个应该直接就return出来 ,boot默认顺序如上
                b.HandlerAdapter 负责实现对各种各样的handler 的适配器调用
                    - RequestMappingHandlerAdapter 处理 @RequestMapping 方法    （控制器方法）
                        参数解析器、返回值处理器体现了组合模式
                    - SimpleControllerHandlerAdapter 处理 Controller 接口    （WelcomePageHandlerMapping,BeanNameUrlHandlerMapping 这种Mapping后面都会找这个适配器）
                    - HandlerFunctionAdapter 处理 HandlerFunction  函数式接口
                    - HttpRequestHandlerAdapter  处理 HttpRequestHandler 接口 （静态资源）
                    这也是经典适配器模式体现
                c.ResourceHttpRequestHandler.setResourceResolvers 这是典型责任链模式体现

         */



    }

}
