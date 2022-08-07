package com.xuan.spring.A17;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

import javax.annotation.PostConstruct;

/**
 *
 */
public class A17_1 {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.registerBean(Config.class);
        context.refresh();
        context.close();
        // Bean的生命周期  构造 初始化 销毁
        // 创建 ->依赖注入-》初始化
        /*
            a.创建代理的时机
                1.初始化之后（无循环依赖时候）
                2.在创建和依赖注入之间创建 （有循环依赖的时候）

        * */

    }

    @Configuration
    static class Config{
        @Bean // 解析@Aspect 产生代理
        public AnnotationAwareAspectJAutoProxyCreator annotationAwareAspectJAutoProxyCreator(){
            return new AnnotationAwareAspectJAutoProxyCreator();
        }

        @Bean // 解析@Autowired
        public AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor(){
            return new AutowiredAnnotationBeanPostProcessor();
        }

        @Bean // 解析@PostConstruct
        public CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor(){
            return  new CommonAnnotationBeanPostProcessor();
        }

        @Bean
        public Advisor advisor(MethodInterceptor advice){
            AspectJExpressionPointcut pointcut =  new AspectJExpressionPointcut();
            pointcut.setExpression("execution(* foo())");
            return new DefaultPointcutAdvisor(pointcut,advice);
        }

        @Bean
        public MethodInterceptor advice(){
            return new MethodInterceptor() {
                @Override
                public Object invoke(MethodInvocation invocation) throws Throwable {
                    System.out.println("before...");
                    return invocation;
                }
            };
       /*     return  (MethodInterceptor invocation) ->{
                System.out.println("before...");
                return  MethodInterceptor.;  };*/

        }

        @Bean
        public Bean1 bean1(){
            return new Bean1();
        }

        @Bean
        public Bean2 bean2(){
            return new Bean2();
        }


    }

    static class Bean1{
        public void foo(){}
        public Bean1(){
            System.out.println("Bean1()");
        }
        @PostConstruct public void  init(){
            System.out.println("Bean1 init()");
        }
    }

    static class Bean2{
        public Bean2(){
            System.out.println("Bean2()");
        }
        @Autowired public  void  setBean1(Bean1 bean1){}
        @PostConstruct public  void init(){
            System.out.println("Bean2 init()");
        }
    }
}
