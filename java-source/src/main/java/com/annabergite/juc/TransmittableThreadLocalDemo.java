package com.annabergite.juc;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransmittableThreadLocalDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
// 额外的处理，生成修饰了的对象executorService
        executorService = TtlExecutors.getTtlExecutorService(executorService);

        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<String>();
        context.set("value-set-in-parent");
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(context.get());
            }
        };
        executorService.submit(task);

        executorService.shutdown();
    }
}
