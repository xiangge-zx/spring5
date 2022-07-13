package com.xuan.spring.A07;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * 实际过程当中3选一即可
 */
public class Bean1  implements InitializingBean {
    public static final Logger log = LoggerFactory.getLogger(Bean1.class);

    @PostConstruct  // 先执行扩展功能的初始化  需要添加@PostConstruct的后处理器
    public  void  init1(){
        log.debug("初始化1");
    }

    @Override // 再执行接口功能的初始化  属于spring自身的方法
    public void afterPropertiesSet() throws Exception {
        log.debug("初始化2");
    }

    public void init3(){  // 最后执行配置
        log.debug("初始化3");
    }

}
