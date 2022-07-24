package com.xuan.spring.A12;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class CglibProxyDemo {

    static  class  Target {
        public void foo() {
            System.out.println("target foo");
        }
    }


    /**
     * cglib
     * @param param
     */
    //  代理是子类型 目标是父类型
    public static void main(String[] param) {

        // 目标对象
        Target target = new Target();

        Target proxy = (Target) Enhancer.create(Target.class,(MethodInterceptor)(p, method, args, methodProxy)->{
            System.out.println("before...");
           // Object result = method.invoke(target,args); // 用方法反射调用
            // methodProxy 它可以避免反射调用
            Object result = method.invoke(target, args); // 内部没有用反射
            System.out.println("after...");
            return result;
        });

        proxy.foo();

    }
}
