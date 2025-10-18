package com.annabergite.juc.alternative.print;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class threadpool {

    public static void main(String[] args) {
        myThreadPoolExecutor threadPoolExecutor = new myThreadPoolExecutor(5, 5, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(100));

        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.valueOf(myThreadPoolExecutor.i));
            }
        });

        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.valueOf(myThreadPoolExecutor.i));
            }
        });
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.valueOf(myThreadPoolExecutor.i));
            }
        });
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.valueOf(myThreadPoolExecutor.i));
            }
        });
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.valueOf(myThreadPoolExecutor.i));
            }
        });
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.valueOf(myThreadPoolExecutor.i));
            }
        });
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.valueOf(myThreadPoolExecutor.i));
            }
        });
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.valueOf(myThreadPoolExecutor.i));
            }
        });
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.valueOf(myThreadPoolExecutor.i));
            }
        });
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.valueOf(myThreadPoolExecutor.i));
            }
        });
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.valueOf(myThreadPoolExecutor.i));
            }
        });
    }

}
