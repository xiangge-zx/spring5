package com.xuan.spring.A05;

import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;

public class MapperPostProcessor implements BeanDefinitionRegistryPostProcessor { // 不用于之前那个atBean使用的接口 这里多了一个方法可以直接用于注册
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    /**
     * bean工厂里名字相同的bean 后生成的就把前生成的覆盖掉了
     *
     * @param beanFactroy
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanFactroy) throws BeansException {
        try {
            PathMatchingResourcePatternResolver resolver =new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath:com/xuan/spring/A05/mapper/**/*.class");
            AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator(); // 名字生成器
            CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
            for (Resource resource:resources) {
                MetadataReader reader = factory.getMetadataReader(resource);
                ClassMetadata classMetadata = reader.getClassMetadata();
                if(classMetadata.isInterface()){ // 判断是不是接口

                   AbstractBeanDefinition bd = BeanDefinitionBuilder.genericBeanDefinition(MapperFactoryBean.class)
                            .addConstructorArgValue(classMetadata.getClassName())  // 设置构造方法参数
                            .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)  //  根据类型自动找Bean工厂内有没有注册好的Bean
                            .getBeanDefinition();
                    String name = generator.generateBeanName(bd,beanFactroy);
                    beanFactroy.registerBeanDefinition(name,bd);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
