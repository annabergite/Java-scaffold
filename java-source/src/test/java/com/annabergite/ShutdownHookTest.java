package com.annabergite;

import org.junit.Test;

/**
 * @PACKAGE_NAME: com.annabergite
 * @USER: annabergite
 * @Description TODO
 * @Date 2023/12/16 17:21
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
public class ShutdownHookTest {

    @Test
    public void test1() {
        // 测试正常退出的情况
        Runtime.getRuntime().addShutdownHook(
                new Thread(
                        () -> {
                            System.out.println("hook1 执行了");
                        })
        );
    }

    @Test
    public void test2() {
        // 测试Hook执行顺序是否真的无序
        Runtime.getRuntime().addShutdownHook(
                new Thread(
                        () -> {
                            System.out.println("hook1 执行了");
                        })
        );

        Runtime.getRuntime().addShutdownHook(
                new Thread(
                        () -> {
                            System.out.println("hook2 执行了");
                        })
        );
    }

    //输出：输出结果hook1和hook2会随机打印，没有固定顺序

    @Test
    public void test3() {
        // 测试kill -9 会执行Hook吗
        Runtime.getRuntime().addShutdownHook(
                new Thread(
                        () -> {
                            System.out.println("hook1 执行了");
                        })
        );

        while (true) {

        }

    }

    @Test
    public void test5() {
        // 测试移除Hook后，会执行Hook吗
        Thread thread = new Thread(() -> {
            System.out.println("hook1 执行了");
        });

        Runtime.getRuntime().addShutdownHook(thread);
        Runtime.getRuntime().removeShutdownHook(thread);
    }

    @Test
    public void test6() {
        // 测试执行halt方法后，会执行Hook吗
        Thread thread = new Thread(() -> {
            System.out.println("hook1 执行了");
        });

        Runtime.getRuntime().addShutdownHook(thread);
        Runtime.getRuntime().halt(111);
    }
    @Test
    public void test7() {
        // 测试已经执行Hook时，还能添加新的hook吗
        Thread thread = new Thread(() -> {
            System.out.println("hook1 执行了");

        });

        Runtime.getRuntime().addShutdownHook(thread);
        Runtime.getRuntime().halt(111);
    }

    @Test
    public void test8() {
        // 测试重复注册后，会执行Hook吗
        Thread thread = new Thread(() -> {
            System.out.println("hook1 执行了");
        });

        Runtime.getRuntime().addShutdownHook(thread);
        Runtime.getRuntime().addShutdownHook(thread);
    }
    @Test
    public void test9() {
        // 测试重复注册后，会执行Hook吗
        Thread thread = new Thread(() -> {
            System.out.println("hook1 执行了");
        });

        thread.start();
        Runtime.getRuntime().addShutdownHook(thread);
    }

}
