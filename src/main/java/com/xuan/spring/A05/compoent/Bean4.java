package com.xuan.spring.A05.compoent;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Bean4 {
    public static final Logger log = LoggerFactory.getLogger(Bean4.class);

    public Bean4(){log.debug("我被 Spring 管理啦");}
}
