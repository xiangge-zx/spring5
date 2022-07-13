package com.xuan.spring.A08;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Scope("session")
@Component
public class BeanForSession {
    public static final Logger log = LoggerFactory.getLogger(BeanForSession.class);


    @PreDestroy 
    public  void  destroy1(){
        log.debug("destory");
    }


}
