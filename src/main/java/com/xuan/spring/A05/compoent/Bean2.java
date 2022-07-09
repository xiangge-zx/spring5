package com.xuan.spring.A05.compoent;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
public class Bean2 {
    public static final Logger log = LoggerFactory.getLogger(Bean2.class);

    public Bean2(){log.debug("我被 Spring 管理啦");}
}
