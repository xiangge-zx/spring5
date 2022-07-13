package com.xuan.spring.A08;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Scope("request")
@Component
public class BeanForRequest  {
    public static final Logger log = LoggerFactory.getLogger(BeanForRequest.class);


    @PreDestroy
    public  void  destroy1(){
        log.debug("destory");
    }


}
