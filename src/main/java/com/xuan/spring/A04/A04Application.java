package com.xuan.spring.A04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Bean后处理器
 *
 * 1.Bean后处理器的作用：为bean生命周期各个阶段提供扩展（实体化前后 依赖注入前后 销毁前后）
 * 2.常见的后处理器
 *
 */
public class A04Application {
    public static void main(String[] args) {
        // GenericApplicationContext是一个干净容器 没有添加后处理器的那些功能
        GenericApplicationContext context = new GenericApplicationContext();

        // 用原始方法注册了三个bean
        context.registerBean("bean1",Bean1.class);
        context.registerBean("bean2",Bean2.class);
        context.registerBean("bean3",Bean3.class);
        context.registerBean("bean4",Bean4.class);

//        报错 No qualifying bean of type 'java.lang.String' available: expected 因为获取@Value里面的值时还需要
        context.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class); // 解析@Autowired和@Value的后处理器
        context.registerBean(CommonAnnotationBeanPostProcessor.class); // @Resource @PostConstruct @PreDestroy

        // 把解析@ConfigurationProperties 的后处理器加在容器中
        // 之所以用这个方法而不是上面的.registerBean 是因为还要加上绑定相关的工厂信息
        ConfigurationPropertiesBindingPostProcessor.register(context.getDefaultListableBeanFactory());


        // 如果有多个后处理器会排序 所以无论用怎样的顺序registerBean 最终都会执行相同的处理
        //  初始化容器，如果加了后处理器会帮你执行好
        context.refresh(); // 初始化所有的单例



        System.out.println(context.getBean(Bean4.class));

        // 销毁容器
        context.close();
    }
}
