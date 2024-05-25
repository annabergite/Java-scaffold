package com.annabergite;

import org.apache.dubbo.remoting.ExecutionException;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTest {

    @Test
    public void test1() throws ExecutionException, InterruptedException, java.util.concurrent.ExecutionException {
        CompletableFuture<String> base = new CompletableFuture<>();
        CompletableFuture<String> future = base.thenApply(s -> s + " 2").thenApply(s -> s + " 3");
        base.complete("1");
        System.out.println(future.get());
    }


    @Test
    public void test2() throws ExecutionException, InterruptedException, java.util.concurrent.ExecutionException {
        CompletableFuture<String> base = new CompletableFuture<>();
        CompletableFuture<String> future = base.thenApply(s -> s + " 2").thenApply(s -> s + " 3");
        future.complete("1");
        System.out.println(future.get());
    }


    @Test
    public void test3() throws java.util.concurrent.ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CompletableFuture<String> step1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行【步骤1】");
            return "【步骤1的执行结果】";
        }, executor);
        CompletableFuture<String> step2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行【步骤2】");
            return "【步骤2的执行结果】";
        }, executor);
        CompletableFuture<String> step3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行【步骤3】");
            return "【步骤3的执行结果】";
        }, executor);

        CompletableFuture<Object> stepN = CompletableFuture.anyOf(step1, step2, step3);
        System.out.println("步骤N的结果：" + stepN.get());

        executor.shutdown();
    }

    @Test
    public void test4() throws java.util.concurrent.ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CompletableFuture<String> step1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行【步骤1】");
            return "【步骤1的执行结果】";
        }, executor);
        CompletableFuture<String> step2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行【步骤2】");
            return "【步骤2的执行结果】";
        }, executor);
        CompletableFuture<String> step3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行【步骤3】");
            return "【步骤3的执行结果】";
        }, executor);

        CompletableFuture<Void> stepN = CompletableFuture.allOf(step1, step2, step3);
        System.out.println("步骤N的结果：" + stepN.get());

        executor.shutdown();
    }

    @Test
    public void test5() throws java.util.concurrent.ExecutionException, InterruptedException {

        // 生产者，可以指定返回结果
        CompletableFuture<String> firstTask = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务开始执行");
            System.out.println("异步任务执行结束");
            return "返回结果";
        });

        String result1 = firstTask.join();
        String result2 = null;
        try {
            result2 = firstTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(result1 + "," + result2);
    }


    @Test
    public void test6() throws IOException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("任务A");
            return "abcdefg";
        }).thenAccept(result -> {
            System.out.println("任务b，拿到结果处理：" + result);
        });

        System.in.read();

    }

    @Test
    public void test7() throws IOException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务A执行");
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("任务B执行");
            return 10;
        }), (r1, r2) -> {
            System.out.println("任务C执行");
            return r1 + r2;
        });
        System.out.println("任务C结果=" + future.join());
    }
}
