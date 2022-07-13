package com.xuan.spring.A09;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "prototype",proxyMode = ScopedProxyMode.TARGET_CLASS) // 注册多例,proxyMode这个属性的底层也是生成代理
@Component
public class F2 {


}
