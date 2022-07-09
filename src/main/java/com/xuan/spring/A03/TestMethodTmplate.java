package com.xuan.spring.A03;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板方法
 */
public class TestMethodTmplate {
    public static void main(String[] args) {
        MyBeanFactory beanFactory = new MyBeanFactory();
//        beanFactory.addBeanPostProcessor(new BeanPostProcessor() { // 匿名内部类写法
//            @Override
//            public void inject(Object bean) {
//                System.out.println("解析 @Autowired");
//            }
//        });
        beanFactory.addBeanPostProcessor(bean -> System.out.println("解析 @Autowired")); // Lambda 表达式
        beanFactory.addBeanPostProcessor(bean -> System.out.println("解析 @Resource")); // 实现了BeanPostProcessor这个接口并打印了一行system.out.println
        beanFactory.getBean();
    }

    // 模板方法  Template Method Pattern
    static class  MyBeanFactory{
        public Object getBean(){
            Object bean = new Object();
            System.out.println("构造"+bean);
            System.out.println("依赖注入"+bean); // @Autowired @Resource
            for (BeanPostProcessor processor:processors) {
                processor.inject(bean); // 遍历执行后处理器 调用inject的时候需要完成bean的依赖注入 需要把原始对象传递进去

            }
            // 这里可能有多个后处理器
            System.out.println("初始化"+bean);
            return bean;
        }
        private List<BeanPostProcessor> processors = new ArrayList<>();

        public void addBeanPostProcessor(BeanPostProcessor processor){
            processors.add(processor);
        }
    }

    static interface BeanPostProcessor{
        public void inject(Object bean); // 对依赖注入阶段的扩展

    }
}
