package com.xuan.spring.A05;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;


/**
 * BeanFactory后处理器
 *
 *
 */
public class A05Application {
    public static void main(String[] args) throws IOException {
        // GenericApplicationContext是一个干净容器 没有添加后处理器的那些功能
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config",Config.class);
//        context.registerBean(ConfigurationClassPostProcessor.class); // @ComponentSacn @Bean @Import @ImportResoure
//        context.registerBean(MapperScannerConfigurer.class,bd->{
//            bd.getPropertyValues().add("basePackage","com.xuan.spring.A05.mapper");
//        }); // @MapperScan



/**
 * @ComponentScan 注解扫描的功能实现
 *
 *
 */
//        ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class); // 找有没有指定注解
//        if(componentScan!=null){
//            for (String p :componentScan.basePackages()) {
//                System.out.println(p);
//                // com.itheima.A05.component -> classpath*:com/itheima/A05/component/**/*.class
//                String path = "classpath*:"+p.replace(".","/")+"**/*.class";
//                System.out.println(path);
//                CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
//                Resource[] resources =context.getResources(path); // 获取资源
//                for (Resource resource:resources){
//                    System.out.println(resource);
//                    MetadataReader reader = factory.getMetadataReader(resource);
//                    System.out.println("类名:"+reader.getClassMetadata().getClassName());
//                    System.out.println("是否加了 @Component"+reader.getAnnotationMetadata().hasAnnotation(Component.class.getName())); // 扫描类是否加了Component注解
//                }
//            }
//        }

        context.registerBean(AtBeanPostProcessor.class); // 在容器初始化之前把我们写的后处理加进去
        context.registerBean(MapperPostProcessor.class); // 解析Mapper接口+

        // 初始化容器
        context.refresh();

        for (String name:context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        // 销毁容器
        context.close();

    }
}
