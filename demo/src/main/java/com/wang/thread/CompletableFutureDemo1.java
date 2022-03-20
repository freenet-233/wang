package com.wang.thread;



import java.util.concurrent.CompletableFuture;

/**
 * @author wangguangpeng
 */
public class CompletableFutureDemo1 {

    private static final double NUMBER = 0.3;

    /**
     * 串行执行多个CompletableFuture任务
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 第一个任务:
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(()->{
            return queryCode("中国石油");
        });

        // cfQuery成功后继续执行下一个任务:
        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice(code);
        });
        // cfFetch成功后打印结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price:" + result);
        });

        cfFetch.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    static String queryCode(String name) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        if (Math.random() < NUMBER) {
            throw new RuntimeException("fetch price failed!");
        }

        return 5 + Math.random() * 20;
    }
}
