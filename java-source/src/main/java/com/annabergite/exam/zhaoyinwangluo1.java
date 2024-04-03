package com.annabergite.exam;

import java.lang.reflect.Method;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @PACKAGE_NAME: com.annabergite.exam
 * @USER: annabergite
 * @Description TODO
 * @Date 2024/3/9 15:39
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/


class Solution {
    static class NamesThreadFactory implements ThreadFactory {
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        public NamesThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            if (null == name || name.isEmpty()) {
                name = "default-worker-";
            }
            namePrefix = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), threadNumber.getAndIncrement());
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    /**
     * @param corePoolSize  线程池的核心线程数
     * @param maxPoolSize   线程池的最大线程数
     * @param queueCapacity 线程池队列的容量
     * @return 线程池
     */
    public static ExecutorService executorService(int corePoolSize, int maxPoolSize, int queueCapacity) {
        ExecutorService threadpool = new ThreadPoolExecutor(corePoolSize,
                maxPoolSize,
                10L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(queueCapacity),
                new NamesThreadFactory(""),
                new ThreadPoolExecutor.AbortPolicy());
        return threadpool;
    }


    public int solution(String methodName, int a, int b) throws Exception {


        if (!"add".equals(methodName) && !"sub".equals(methodName) && !"mul".equals(methodName) && !"div".equals(methodName)) {

            throw new NoSuchMethodException();
        }
        Class<Calculator> clazz = Calculator.class;
        int result;
        try {
            Method method = clazz.getMethod(methodName, int.class, int.class);
            result = (int) method.invoke(null, a, b);
        } catch (Exception e) {
            throw new NoSuchMethodException();
        }
        return result;
    }
}

class Calculator {
    public static int add(int a, int b) {
        return a + b;
    }

    public static int sub(int a, int b) {
        return a - b;
    }

    public static int mul(int a, int b) {
        return a * b;
    }

    public static int div(int a, int b) {
        return a / b;
    }
}