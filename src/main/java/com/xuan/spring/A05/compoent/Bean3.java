package com.xuan.spring.A05.compoent;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class Bean3 {
    public static final Logger log = LoggerFactory.getLogger(Bean3.class);

    public Bean3(){log.debug("我被 Spring 管理啦");}
}
