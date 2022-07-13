package com.xuan.spring.A09;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class E {

    @Lazy // 加了@lazy过后 就不是真的把F1注入进来 而是加了一个代理 请求这个代理的时候回去找多例的bean
    @Autowired
    private F1 f1;

    @Autowired
    private F2 f2;

    /*
    * 一般来说不用代理 有一个的性能损耗
    * 用容器或者工厂去拿多例更加简洁
    * */
    @Autowired
    private ObjectFactory<F3> f3; // 通过工厂来识别F3的多例

    @Autowired
    private ApplicationContext context;


    public F1 getF1(){return f1;}

    public F2 getF2(){return f2;}

    public F3 getF3(){
        return f3.getObject();}

    public F4 getF4(){
        return context.getBean(F4.class);}

}
