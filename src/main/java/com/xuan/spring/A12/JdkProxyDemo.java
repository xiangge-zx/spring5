package com.xuan.spring.A12;

import org.springframework.cglib.proxy.InvocationHandler;


import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyDemo {

    interface  Foo{
        void  foo();
    }

    static  class  Target implements Foo{
        @Override
        public void foo() {
            System.out.println("target foo");
        }
    }

    // jdk 只能针对接口代理
    // cglib
    public static void main(String[] args) {

        // 目标对象
        Target target = new Target();

        ClassLoader loader =JdkProxyDemo.class.getClassLoader(); // 用来加载在运行期间动态生成的字节码
        // 参数二 : 数组代表着以后实现的代理类型可以一次实现多个接口
        // 参数三 ：代理方法的实现，行为等
        Foo proxy = (Foo) Proxy.newProxyInstance(loader, new Class[]{Foo.class}, (proxy1, method, objects) -> { // 代理类有用到父方法 所以可以直接强转
            System.out.println("before...");
            // 目标.方法（参数）
            // 方法.invoke(目标,参数)
            Object result = method.invoke(target, args);
            System.out.println("after...");
            return result;
        });

        proxy.foo();
    }
}
