package com.xuan.spring.A04;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 *
 */
// springboot 中有着属性绑定的功能，将来会根据这个前缀配合属性名称去匹配键值信息，匹配到了就进行一个绑定
// 如下就会匹配到java.home java.version的键值信息
@ConfigurationProperties(prefix = "java")
public class Bean4 {

    private String home;

    private String version;

    public String getHome(){
        return home;
    }

    private void setHome(String home){
        this.home=home;
    }
}
