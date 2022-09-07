package com.xuan.spring.A27;


import com.xuan.spring.A09.A09Application;
import com.xuan.spring.A26.WebConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.annotation.RequestHeaderMethodArgumentResolver;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.*;

import java.lang.reflect.Method;
import java.util.List;

public class A27 {
    private static final  Logger log = LoggerFactory.getLogger(A27.class);

    public static void main(String[] args) throws Exception {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        Method method = Controller.class.getMethod("test1");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller); // 获取返回值

        // 1.测试返回值类型为 ModelAndView
//        HandlerMethodReturnValueHandlerComposite composite  = getR

        // 2.返回值类型为 string类型时




    }

    private  static  void renderView(AnnotationConfigApplicationContext context, ModelAndViewContainer container, ServletWebRequest webRequest) throws  Exception{

    }

    static  class  Controller{
        private static final  Logger log = LoggerFactory.getLogger(Controller.class);


        public static HandlerMethodReturnValueHandlerComposite getArgumentResolver(AnnotationConfigApplicationContext context){
            HandlerMethodReturnValueHandlerComposite composite = new HandlerMethodReturnValueHandlerComposite();
            composite.addHandler(new ModelAndViewMethodReturnValueHandler());
            composite.addHandler(new ViewNameMethodReturnValueHandler());
            composite.addHandler(new ServletModelAttributeMethodProcessor(false));
//            composite.addHandler(new HttpEntityMethodProcessor(List.of))
            composite.addHandler(new HttpHeadersReturnValueHandler()); // mavContainer.setRequestHandled(true); 表示已经处理过
//            composite.addHandler(new RequestResponseBodyMethodProcessor());
            composite.addHandler(new ServletModelAttributeMethodProcessor(true));
            return  composite;

        }

        /*字符串 模型数据 会走视图渲染
        * test1 -- test4
        *
        * 后三种不会走视图渲染
        * 是走message Conv。。  完成消息转换
        *
        *
        *
        * */
        public ModelAndView test1(){
            log.debug("test1()");
            ModelAndView mav = new ModelAndView("view1");
            mav.addObject("name","张三");
            return  mav;
        }

        public String test2(){
            log.debug("test2()");
            return  "view2";
        }

        @ModelAttribute
//        @RequestMapping("/test3")  没有视图名的情况下 会把请求路径当成是视图名
        public User test3(){
            log.debug("test3()");
            return new User("李四",20);
        }

        public User test4(){
            log.debug("test4()");
            return  new User("王五",30);
        }

        /*
        *  test5 -- test7  本身已经代表响应了 不需要再走解析和渲染流程
        *
        *
        * */
        public HttpEntity<User> test5(){
            log.debug("test5()");
            return  new HttpEntity<>(new User("赵六",40));
        }

        public HttpHeaders test6(){
            log.debug("test6()");
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type","text/html");
            return  headers;
        }

        @ResponseBody
        public User test7(){
            log.debug("test7()");
            return  new User("钱七",50);
        }




    }





    static class User{
        private String name;
        private  int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



}
