package com.xuan.spring.A15;

import com.xuan.spring.A09.E;
import org.aopalliance.intercept.MethodInterceptor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 *  初始化和销毁的执行顺序
 */

public class A15 {

    public static final Logger log = LoggerFactory.getLogger(A15.class);

    @Aspect
    static class MyAspect{ // 一个aspect切面可以包含一组或多组切点

        @Before("execution(* foo())")  // 表示任意类中的 foo()方法
        public void before(){
            System.out.println("前置增强");
        }

        @After("execution(* foo())")  // 表示任意类中的 foo()方法
        public void after(){
            System.out.println("后置增强");
        }
    }

    public static void main(String[] args) {
        // ctrl+alt+b 查看接口的实现
        /*
        * 两个切面概念
        * aspect =
        *      通知1（advice） +切点1（pointcut）
        *      通知2（advice） +切点2（pointcut）
        *      通知3（advice） +切点3（pointcut）
        *      ...
        * advisor = 更细粒度的切面，包含一个通知和切点
        * aspect会被拆解成多个advisor
        * */

        // 1.备好切点
        AspectJExpressionPointcut pointcut =new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* foo())");
        // 2.备好通知 (下面是环绕通知) 不同于cglib的同名方法
        MethodInterceptor  advice = invocation -> {
            System.out.println("before..");
            Object result = invocation.proceed(); // 调用目标
            System.out.println("after..");
            return  result;
        };
        // 3.备好切面
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut,advice); // 切点 通知都封装在切面里

        /*
        * 4.创建代理
        * jdk必须有接口了才能生成代理
        * a.proxyTargetClass = false,目标实现了接口,用jdk实现
        * b.proxyTargetClass = false,目标没有实现了接口,用cglib实现
        * c.proxyTargetClass = true,总用jdk实现
        * */
        Target1 target = new Target1();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvisor(advisor); // 一个代理可以添加一个或多个切面
        factory.setInterfaces(target.getClass().getInterfaces());
        I1  proxy = (I1) factory.getProxy();
        System.out.println(proxy.getClass());
        proxy.foo();
        proxy.bar();
        /*
        * a.spring的代理选择规划
        * b.底层切点实现
        * c.底层通知实现
        * d.proxyFactory 是用来创建代理的核心实现，用AopProxyFactory代表具体代理实现
        * - JdkDynamicAopProxy
        * - ObjenesisCglibAopProxy
        *
        * */

    }

    interface I1{
        void foo();

        void bar();
    }

    static class Target1 implements I1 {

        @Override
        public void foo() {
            System.out.println("target1 foo");
        }

        @Override
        public void bar() {
            System.out.println("target1 bar");
        }
    }

    static class Target2 implements I1 {

        @Override
        public void foo() {
            System.out.println("target2 foo");
        }

        @Override
        public void bar() {
            System.out.println("target2 bar");
        }
    }



}
