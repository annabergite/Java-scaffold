//package com.annabergite.springcli.source;
//
///**
// * @PACKAGE_NAME: com.annabergite.springcli.source
// * @USER: annabergite
// * @Description TODO
// * @Date 2023/12/26 22:17
// * @Version 1.0
// * @PROJECT_NAME: Java-scaffold
// **/
//import redis.clients.jedis.Jedis;
//
//public class DistributedLockExample {
//
//    private Jedis jedis;
//
//    public DistributedLockExample() {
//        jedis = new Jedis("localhost", 6379);
//    }
//
//    public boolean acquireLock(String lockKey, String requestId, int expireTime) {
//        Long result = jedis.setnx(lockKey, requestId);
//        if (result == 1) {
//            jedis.expire(lockKey, expireTime);
//            return true;
//        }
//        return false;
//    }
//
//    public void releaseLock(String lockKey, String requestId) {
//        String storedRequestId = jedis.get(lockKey);
//        if (storedRequestId != null && storedRequestId.equals(requestId)) {
//            jedis.del(lockKey);
//        }
//    }
//
//    public static void main(String[] args) {
//        DistributedLockExample lockExample = new DistributedLockExample();
//
//        String lockKey = "resource:lock";
//        String requestId = "request123";
//        int expireTime = 60; // 锁的过期时间
//
//        if (lockExample.acquireLock(lockKey, requestId, expireTime)) {
//            try {
//                // 执行需要加锁的操作
//                System.out.println("Lock acquired. Performing critical section.");
//                Thread.sleep(1000); // 模拟操作耗时
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                lockExample.releaseLock(lockKey, requestId);
//                System.out.println("Lock released.");
//            }
//        } else {
//            System.out.println("Failed to acquire lock.");
//        }
//    }
//}
