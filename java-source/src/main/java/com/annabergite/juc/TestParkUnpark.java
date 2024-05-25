package com.annabergite.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Logger;

//@Slf4j(topic = "c.TestParkUnpark")
public class TestParkUnpark {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("start.........");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("park..........");
            LockSupport.park();
            System.out.println("resume.........");

        }, "t1");
        t1.start();


        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("unpark......");

        LockSupport.unpark(t1);


    }
}
