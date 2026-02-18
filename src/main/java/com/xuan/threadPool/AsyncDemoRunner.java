package com.xuan.threadPool;

import java.util.concurrent.CompletableFuture;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xuan.threadPool.AsyncDemoService;

public class AsyncDemoRunner {

    public static void main(String[] args) throws Exception {
        // 扫描 com.xuan.threadPool 包，能找到 ExecutorConfig + AsyncDemoService
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.xuan.threadPool");

        AsyncDemoService asyncDemoService = context.getBean(AsyncDemoService.class);

        for (int i = 1; i <= 5; i++) {
            asyncDemoService.doAsyncTask("task-" + i);
        }

        CompletableFuture<String> f1 = asyncDemoService.doAsyncTaskWithResult("task-with-result-1");
        CompletableFuture<String> f2 = asyncDemoService.doAsyncTaskWithResult("task-with-result-2");

        System.out.println("异步返回1 = " + f1.get());
        System.out.println("异步返回2 = " + f2.get());

        Thread.sleep(5000L);
        context.close();
    }
}