package com.annabergite.gupao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.*;

public class JMM {

    public static void main(String[] args) throws FileNotFoundException {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                20,  // 核心线程数量
                20,              //最大线程数
                60,             //空闲临时线程最大存活时间（数值）
                TimeUnit.SECONDS,//空闲临时线程最大存活时间（单位）
                new ArrayBlockingQueue<>(1_000_000_00),//任务队列，也就是一个堵塞队列，也可以使用LinkedBlockingQueue这个阻塞队列
                Executors.defaultThreadFactory(),//用线程池工具类Executors创建线程的工厂
                new ThreadPoolExecutor.AbortPolicy()//任务的拒绝策略中其中一个，丢弃任务并抛出RejectedExecutionException
        );

        Map<String, Integer> hashMap = new ConcurrentHashMap<>();
        File file = new File("C:\\Users\\zhubaiyi\\Desktop\\罗马假日英文剧本.txt");

        System.out.println(executorService.getActiveCount());

        List<CompletableFuture> futureList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                if (s.length == 0) {
                    continue;
                }
                // 创建CompletableFuture，并将任务提交给线程池
                futureList.add(CompletableFuture.supplyAsync(() -> {
                    List<String> results = Arrays.asList(s);
                    results.forEach(x -> {
                        if (!(Objects.equals(x, "") || x == null)) {
                            hashMap.computeIfAbsent(x, k -> 0);
                            hashMap.computeIfPresent(x, (k, v) -> v + 1);
                        }
                    });
                    return null;
                }, executorService));
            }
            CompletableFuture.allOf(futureList.toArray(futureList.toArray(new CompletableFuture[0]))).whenComplete((v, th) -> {
                hashMap.forEach((k, value) -> {
                    System.out.println(k + " " + value);
                });
            }).join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
