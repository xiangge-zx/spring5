package com.xuan.spring.A04;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.env.StandardEnvironment;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * AutowiredAnnotationBeanPostProcessor是流程 先 找到bean里面的@Autowired注解
 * 然后在注入
 */
public class DigInAutowired {
    public static void main(String[] args) throws Throwable {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerSingleton("bean2",new Bean2()); // 一个单例已经创建好了，依赖注入，初始化
        beanFactory.registerSingleton("bean3",new Bean3());
        beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver()); // 可以获取@Value中的值
        beanFactory.addEmbeddedValueResolver(new StandardEnvironment()::resolvePlaceholders); // ${}的解析器

        // 1.查找哪些属性、方法加了@Autowired 后处理器要找bean，所以还是会用到BeanFactory
        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        processor.setBeanFactory(beanFactory);

        Bean1 bean1 = new Bean1();
//        System.out.println(bean1);
//        processor.postProcessProperties(null,bean1,"bean1");
//        System.out.println(bean1);

        Method findAutowiringMetadata= AutowiredAnnotationBeanPostProcessor.class.getDeclaredMethod("findAutowiringMetadata", String.class, Class.class, PropertyValues.class); // 获取后处理器的方法,用于研究是如何解析@Autowire
        findAutowiringMetadata.setAccessible(true);
        InjectionMetadata metedata= (InjectionMetadata) findAutowiringMetadata.invoke(processor,"beean1",Bean1.class,null);

         // 2 调用InjectionMetadata 来进行依赖入住
        metedata.inject(bean1,"bean1",null);
        System.out.println(bean1);

        // 3 如何按类型查找值,从工厂里面把要注入的类型找到了
        Field bean3 = Bean1.class.getDeclaredField("bean3");
        DependencyDescriptor dd1 = new DependencyDescriptor(bean3,false);
        Object o = beanFactory.doResolveDependency(dd1,null,null,null); // doResolveDependency找要注入对象的bean或者value值
        System.out.println(o); // com.xuan.spring.A04.Bean3@2c767a52
    }
}
