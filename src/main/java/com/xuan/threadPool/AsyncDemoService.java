package com.xuan.threadPool;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncDemoService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncDemoService.class);

    @Async("asyncServiceExecutor")
    public void doAsyncTask(String taskName) {
        String threadName = Thread.currentThread().getName();
        logger.info("开始执行异步任务: {} | 线程: {} | 时间: {}", taskName, threadName, LocalDateTime.now());
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("异步任务被中断: {}", taskName, e);
        }
        logger.info("结束执行异步任务: {} | 线程: {} | 时间: {}", taskName, threadName, LocalDateTime.now());
    }

    @Async("asyncServiceExecutor")
    public CompletableFuture<String> doAsyncTaskWithResult(String taskName) {
        String threadName = Thread.currentThread().getName();
        logger.info("[有返回] 开始执行异步任务: {} | 线程: {} | 时间: {}", taskName, threadName, LocalDateTime.now());
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("[有返回] 异步任务被中断: {}", taskName, e);
        }
        String result = "任务[" + taskName + "]执行完成，线程：" + threadName;
        logger.info("[有返回] 结束执行异步任务: {} | 线程: {} | 时间: {}", taskName, threadName, LocalDateTime.now());
        return CompletableFuture.completedFuture(result);
    }
}