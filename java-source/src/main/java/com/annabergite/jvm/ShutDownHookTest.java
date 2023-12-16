package com.annabergite.jvm;

import org.junit.Test;

/**
 * @PACKAGE_NAME: com.annabergite.jvm
 * @USER: annabergite
 * @Description TODO
 * @Date 2023/12/16 16:48
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
/*
Shutdown Hook的特性
Shutdown Hook不能保证一定执行
如果JVM crashe了, Shutdown Hook并不能保证一定执行。例如如果收到了SIGKILL的时候，程序会立刻终止执行，JVM立刻退出，导致没有机会执行Shutdown Hook.如果调用了Runtime.halt()的情况下，也可以导致JVM在没有执行Shutdown Hook的时候直接退出。当然，如果一个程序正常结束，会在JVM退出去调用Shutdown Hook。如果JVM因为用户要求中断或者是接受到SIGTERM，也是会调用Shutdown Hook的。

Shutdown Hook是可以被强制中断的
即使已经开始执行Shutdown Hook,也是可以被中断的，比如当接收到了SIGTERM，但是shutdown hook没有在一定时间内完成，也是会被强制中断，导致shutdown hook没有完整执行。所以一般在Shutdown hook中的操作都应该是可以快速执行完毕，不应该是一些long time的任务。

Shutdown Hook可以有多个
一个程序中可以注册多个shutdown hook，但是JVM执行shutdown hook的时候是一个任意顺序，并且JVM执行hook的时候是并发的。

Shutdown hook中不可以regist/unregister shutdown hook
如果这么做了，JVM会抛IllegalStateException。

Shutdown hook可以被停止
如果一个Shutdown已经开始执行了，除了例如SIGKILL这样的外部干预，需要且只能通过Runtime.halt()中断。

Shutdown hook需要安全权限
Shutdown hook在Spark中的应用实例
 */
public class ShutDownHookTest {
    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Shutdown Hook now!");
            }
        });
        System.out.println("Going to exit");
    }

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

}