package com.xuan.spring.A05;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.Set;

public class AtBeanPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        try {
            CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
            MetadataReader reader = factory.getMetadataReader(new ClassPathResource("com/xuan/spring/A05/Config.class")); // （这一步是读取原数据信息）getMetadataReader方法是不走类加载的,效率也比反射搞
            Set<MethodMetadata> methods =  reader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName()); // 获取所有注解 里面 有Bean.class注解的方法
            for (MethodMetadata method:methods) { // 遍历每一个方法 依次注入Bean
                System.out.println(method);
                String initMethod = method.getAnnotationAttributes(Bean.class.getName()).get("initMethod").toString(); // 对Bean注解里面的参数进行解析扫描

                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
                builder.setFactoryMethodOnBean(method.getMethodName(),"config"); // 工厂方法要指定是那个Bean的工厂方法
                builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);  // 对于构造方法的参数 以及对于工厂方法的参数 如果想自动装配 选择的装配模式为AUTOWIRE_CONSTRUCTOR
                if(initMethod.length()>0){
                    builder.setInitMethodName(initMethod); // 将初始化方法的解析也加入bean工厂
                }
                AbstractBeanDefinition  bd  = builder.getBeanDefinition();

                if (configurableListableBeanFactory instanceof DefaultListableBeanFactory ) { // 类型转换成功再执行注册
                    DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)configurableListableBeanFactory;
                    beanFactory.registerBeanDefinition(method.getMethodName(),bd);  // 方法名就作为Bean的名字  ()
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
