package com.xuan.threadPool;

import java.util.concurrent.*;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);


        for (int i = 0; i < 10; i++) {
            completionService.submit(() -> {
                int result = (int) (Math.random() * 100);
                Thread.sleep(1000);  // 模拟任务执行
                return result;
            });
        }


        for (int i = 0; i < 10; i++) {
            try {
                Future<Integer> future = completionService.take();  // 阻塞直到有任务完成
                Integer result = future.get();
                System.out.println("Task completed with result: " + result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }
}
