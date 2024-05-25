package com.annabergite.juc;

public class OrderPrintTest {
    public static OrderPrint orderPrint = new OrderPrint(0);

    static class OushuRunnable implements Runnable {
        @Override
        public void run() {
            orderPrint.Oushu();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    static class JishuRunnable implements Runnable {
        @Override
        public void run() {
            orderPrint.Jishu();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new JishuRunnable()).start();

        new Thread(new OushuRunnable()).start();
    }


}
/*
输出：
        。。。
Thread-1print: 94
Thread-0print: 95
Thread-1print: 96
Thread-0print: 97
Thread-1print: 98
Thread-0print: 99
Thread-1print: 100
偶数打印完毕, Thread-1==结束
奇数打印完毕, Thread-0==结束*/
