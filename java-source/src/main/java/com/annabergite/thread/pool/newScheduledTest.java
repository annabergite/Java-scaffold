package com.annabergite.thread.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class newScheduledTest {
    ExecutorService executorService = Executors.newScheduledThreadPool(10);
    Future<String> scheduled = executorService.submit(() -> {
        return "1";
    });
}
