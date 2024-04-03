package com.annabergite.springcli.redis;

import redis.clients.jedis.Jedis;
/**
 * @PACKAGE_NAME: com.annabergite.springcli.redis
 * @USER: annabergite
 * @Description TODO
 * @Date 2024/2/17 20:34
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/

public class RedLockExample {

    private static final int QUORUM = 3;
    private static final int LOCK_TIMEOUT = 500;

    private Jedis[] jedisInstances;

    public RedLockExample() {
        jedisInstances = new Jedis[]{
                new Jedis("localhost", 6379),
                new Jedis("localhost", 6380),
                new Jedis("localhost", 6381)
        };
    }

    public boolean acquireLock(String lockKey, String requestId) {
        int votes = 0;
        long start = System.currentTimeMillis();

        while ((System.currentTimeMillis() - start) < LOCK_TIMEOUT) {
            for (Jedis jedis : jedisInstances) {
                if (jedis.setnx(lockKey, requestId) == 1) {
                    jedis.expire(lockKey, LOCK_TIMEOUT / 1000); // 设置锁的超时时间
                    votes++;
                }
            }

            if (votes >= QUORUM) {
                return true;
            } else {
                // 未获取到足够的票数，释放已获得的锁
                for (Jedis jedis : jedisInstances) {
                    if (jedis.get(lockKey).equals(requestId)) {
                        jedis.del(lockKey);
                    }
                }
            }

            try {
                Thread.sleep(50); // 等待一段时间后重试
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return false;
    }

    public void releaseLock(String lockKey, String requestId) {
        for (Jedis jedis : jedisInstances) {
            if (jedis.get(lockKey).equals(requestId)) {
                jedis.del(lockKey);
            }
        }
    }

    public static void main(String[] args) {
        RedLockExample redLockExample = new RedLockExample();

        String lockKey = "resource:lock";
        String requestId = "request123";

        if (redLockExample.acquireLock(lockKey, requestId)) {
            try {
                // 执行需要加锁的操作
                System.out.println("Lock acquired. Performing critical section.");
                Thread.sleep(1000); // 模拟操作耗时
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                redLockExample.releaseLock(lockKey, requestId);
                System.out.println("Lock released.");
            }
        } else {
            System.out.println("Failed to acquire lock.");
        }
    }
}
