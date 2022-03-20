package com.wang.thread;

import java.util.concurrent.CompletableFuture;

/**
 * @author wangguangpeng
 */
public class CompletableFutureDemo {

    private static final double NUMBER = 0.3;

    /**
     * 并行 执行多个CompletableFuture任务
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> cfQuery1 = CompletableFuture.supplyAsync(()->{
            return queryCode("中国石油", "url1");
        });
        CompletableFuture<String> cfQuery2 = CompletableFuture.supplyAsync(()->{
            return queryCode("中国石油", "url2");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQuery1, cfQuery2);
        // 两个CompletableFuture执行异步查询:
        CompletableFuture<Double> cfFetchFromUrl1 = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "url1");
        });

        CompletableFuture<Double> cfFetchFromUrl2 = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "url2");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFromUrl1, cfFetchFromUrl2);
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        });

        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    static String queryCode(String name, String url) {
        System.out.println("query code from url:" + url);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code, String url) {
        System.out.println("query price from url:" + url);
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }

        return 5 + Math.random() * 20;
    }
}
