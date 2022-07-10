package com.xuan.spring.A06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.Resource;


public class MyBean implements BeanNameAware, ApplicationContextAware, InitializingBean { // 先执行Aware回调 再执行初始化方法

    public static final Logger log = LoggerFactory.getLogger(MyBean.class);


    @Override
    public void setBeanName(String name) {
        log.debug("当前Bean "+this+" 名字叫:"+name);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.debug("当前Bean "+this+" 容器是:"+applicationContext);

    }

    @Resource
    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("当前Bean "+this+" 初始化");

    }
}
