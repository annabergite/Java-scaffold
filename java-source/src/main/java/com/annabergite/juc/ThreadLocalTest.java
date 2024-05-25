package com.annabergite.juc;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 3; i++) {
            int j = i;
            executorService.execute(() -> {
                System.out.println(j + "-before：" + SaleHouse.local.get());
                Integer integer = new Random().nextInt(5);
                SaleHouse.local.set(integer);
                System.out.println(j + "-after：" + SaleHouse.local.get());
            });
        }
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("==============================================");
        for (int i = 0; i < 3; i++) {
            int j = i;
            new Thread(() -> {
                try {
                    Integer integer = new Random().nextInt(5);
                    SaleHouse.threadLocal.set(integer);
                    SaleHouse.add(integer);
                    System.out.println(j + "号：" + SaleHouse.threadLocal.get());
                } finally {
                    SaleHouse.threadLocal.remove();
                }

            }).start();
        }
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("总计：" + SaleHouse.total);
    }

    static class SaleHouse {
        static ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return 0;
            }
        };
        public static final ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);
        static int total;

        public static synchronized void add(Integer i) {
            total += i;
        }
    }
}
