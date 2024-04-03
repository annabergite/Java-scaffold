package com.annabergite.juc;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @PACKAGE_NAME: com.annabergite.juc
 * @USER: annabergite
 * @Description TODO
 * @Date 2024/2/25 21:53
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
public class reentrantLockErrorTest { public static void main(String[] args) {

    ReentrantLock reentrantLock = new ReentrantLock();
    new Thread(() -> {
        try {
            reentrantLock.lock();
            System.out.println("第1次获取这个锁，这个锁是:"+reentrantLock);

            int index = 1;
            while (true) {
                try {
                    reentrantLock.lock();
                    System.out.println("第"+(++index)+"次获取这个锁，这个锁是:"+reentrantLock);

                    if (index == 10 ) {
                        break;
                    }
                } finally {
//                        reentrantLock.unlock();  //锁少释放一次
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }).start();


    /**
     * 构建第二个线程 看是否可以获取锁
     */
    new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                reentrantLock.lock();
                for (int i = 0; i < 5; i++) {
                    System.out.println("threadName:" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(new Random().nextInt(200));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                reentrantLock.unlock();
            }
        }
    }).start();


}
}
