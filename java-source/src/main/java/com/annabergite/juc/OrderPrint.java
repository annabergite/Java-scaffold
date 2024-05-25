package com.annabergite.juc;

import cn.hutool.core.thread.ThreadUtil;

public class OrderPrint {

    public int number;

    public OrderPrint(int number) {
        this.number = number;
    }

    public synchronized void Oushu() {
        Thread thread = Thread.currentThread();
        while (number < 101 && thread.isInterrupted() == false) {
            if (number % 2 == 0) {
                System.out.println(thread.getName() + "print: " + number);
                number++;
                ThreadUtil.sleep(100);
                notify();
                if (number == 101) { // 判断是否已打印最后一个数字，如果是则通知线程你该关闭了
                    thread.interrupt();
                }
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
        }
        System.out.println("偶数打印完毕, " + thread.getName() + "==结束");
    }

    public synchronized void Jishu() {
        Thread thread = Thread.currentThread();
        while (number < 101 && thread.isInterrupted() == false) {
            if (number % 2 == 1) {
                System.out.println(thread.getName() + "print: " + number);
                number++;
                ThreadUtil.sleep(20);
                notify();
                if (number == 99) {   // 判断是否已打印最后一个数字，如果是则通知线程你该关闭了
                    thread.interrupt();
                }
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
        }
        System.out.println("奇数打印完毕, " + thread.getName() + "==结束");

    }
}
