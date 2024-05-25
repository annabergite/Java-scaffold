package com.annabergite;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class FakeWakeUp {
    @Test
    public void fakeWakeUp1() {//A:num+1
        AtomicInteger data = new AtomicInteger();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.incrementAndGet();
            }
        }, "A").start();

        //C:num+1
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.incrementAndGet();
            }
        }, "C").start();
        System.out.println(data.get());
    }
}
