package com.annabergite.springcli;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

/**
 * @PACKAGE_NAME: com.annabergite.springcli
 * @USER: annabergite
 * @Description TODO
 * @Date 2024/1/29 21:14
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
@SpringBootTest
public class executor {
    @Test
    void handwritingexecutor(String[] args) {

        ExecutorService threadpool = new ThreadPoolExecutor(
                2,//corePoolSize
                5,//maximumPoolSize
                3l,//keepAliveTime
                TimeUnit.SECONDS,//unit
                new ArrayBlockingQueue<Runnable>(3),//workQueue(等待队列)
                Executors.defaultThreadFactory(),//threadFactory
                //handler
                //new ThreadPoolExecutor.AbortPolicy()
                //new ThreadPoolExecutor.CallerRunsPolicy()
                //new ThreadPoolExecutor.DiscardOldestPolicy()
                new ThreadPoolExecutor.DiscardPolicy()
        );//这个线程池最多可以让五个任务同时进行，并且还可以让三个任务等待执行，瞬时的任务处理数量最大是8。

        for (int i = 1; i <= 9; i++) {
            threadpool.execute(() -> {
                        System.out.println(Thread.currentThread().getName() + "号线程窗口处理任务中... ... ... ..." + "当前时间" + System.currentTimeMillis());
                        try {
                            Thread.sleep(1000);
                            System.out.println(Thread.currentThread().getName() + "号线程处理完毕");
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
            );
        }
        threadpool.shutdown();
    }
}
