package com.xuan.spring.A04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

// 构造方法—》依赖注入的注解 –》初始化方法—》销毁方法
public class Bean1 {
    private static final Logger logger = LoggerFactory.getLogger(Bean1.class);

    @PostConstruct // 翻译是实例化过后 但实际上是在初始化之前解析的（Bean的生命周期）
    public void init(){
        logger.debug("@PostConstruct 生效");
    }

    private Bean2 bean2;

    @Autowired
    public void setBean2(Bean2 bean2){
        logger.debug("@Autowired 生效: {}",bean2);
        this.bean2 = bean2;
    }

    private Bean3 bean3;

    @Resource
    public void  setBean3(Bean3 bean3){
        logger.debug("@Resource 生效 : {}",bean3);
        this.bean3= bean3;
    }

    private String home;

    /**
     * @Value配合@Autowired 实现一个值的注入
     * @Autowired 写在方法上可以打印一些信息,(一般工作中是直接写在属性上的)
     * @param home
     */
    @Autowired
//    public void setHome(UserService service){ // @Autowired加在方法上主要是属性匹配容器中的Bean，看容器中有没有这样一个Bean 有的话就依赖注入
    // 但是属性是字符串类型就会匹配失败
    public void setHome(@Value("${JAVA_HOME}") String home){
        logger.debug("@Value 生效 {}",home);
        this.home=home;
    }



    @PreDestroy
    public void destroy(){
        logger.debug("@PreDestroy 生效");
    }

   public String toString(){
        return "Bean1{" +
                "bean2"+bean2+
                ",bean3"+bean3+
                ",home="+home+'\''+
                '}';
   }

}

