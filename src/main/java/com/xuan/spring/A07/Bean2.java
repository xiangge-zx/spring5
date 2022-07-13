package com.xuan.spring.A07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

public class Bean2 implements DisposableBean {
    public static final Logger log = LoggerFactory.getLogger(Bean2.class);

    // 第六讲的aware应该在这之前

    @PreDestroy  // 先执行扩展功能的销毁
    public  void  destroy1(){
        log.debug("销毁1");
    }

    @Override
    public void destroy() throws Exception {
        log.debug("销毁2");
    }

    public void destroy3(){  // 最后执行配置
        log.debug("销毁3");
    }


}
