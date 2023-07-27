package com.xuan.spring.A39;

public class A39 {
    // springboot启动流程 12步骤

    // 第一步 创建事件发布器
    // SpringApplicationRunListeners listeners = getRunListeners(args); // 从springFactory中获取

    // 第二步 args 封装成参数对象 比如命令行输入的参数
    // ApplicationArguments applicationArguments  = new DefaultApplicationArguments(args)

    // 第三步 获取环境参数
    // ConfigurableEnvironment environment = getOrCreateEnvironment();

    // 第四步  命名不规范的处理
    // ConfigurationPropertySources.attach(environment)

    // 第五步 调用@post 等等做功能增强 ,（application.properties等等）
    // listeners.environmentPrepared(bootstrapContext,enviromnet)

    // 第六步 处理spring.main为前置的参数 进行绑定
    // bindToSpringApplication(environment);

    // 第七步 打印Banner信息
    // Banner printedBanner = printBanner(environment);

    // 第八步 创建spring容器 选择一种容器实现
    // context = createApplicationContext();

    // 第九步 创建一些初始化器 对spring容器进行一些增强
    // applyInitializers(context)
    // listeners.contextPrepared(context); // 初始化完成后 发布事件

    // 第十步  获取xml 等等bean的源,装载进入spring容器
    // Set<Object> sources = getAllSources();
    // Assert.notEmpty(sources,"Sources must not be empty");
    // load(context,sources,toArray(new Object[0]));
    // listeners.contextLoaded(context); // 加载完成后要再发布一个事件

    // 第十一步  初始化各种后处理器 创建单例
    // refreshContext(context);
    // listeners.started(context); // 初始化之后发布事件

    // 第十二步 读取特定的两个
    // callRunners(context,applicationArguments)
}
