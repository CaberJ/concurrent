package cn.caber.concurrent.utils;


import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class RedisLockUtil {

    private static RedissonClient redissonClient = null;

    private static ConcurrentHashMap<String, RLock> redisLockMap;

    static {
        if (Objects.isNull(redissonClient)) {
            redissonClient = (RedissonClient) SpringUtil.getBean(RedissonClient.class);
        }
    }

    /**
     * 阻塞，直到获取锁
     * @param lockName
     */
    public static void lock(String lockName) {
        RLock redisLock = redisLockMap.get(lockName);
        if (Objects.isNull(redisLock)) {
            redisLock = redissonClient.getLock(lockName);
        }
        redisLock.lock();

    }
    /**
     * 阻塞，直到获取锁，最多leaseTime秒后自动解锁
     * @param lockName
     */
    public static void lock(String lockName, Long leaseTime) {
        if (Objects.isNull(leaseTime)) {
            leaseTime = 10L;
        }
        RLock redisLock = redisLockMap.get(lockName);
        if (Objects.isNull(redisLock)) {
            redisLock = redissonClient.getLock(lockName);
        }
        redisLock.lock(leaseTime, TimeUnit.SECONDS);

    }



    /**
     * 尝试获取锁，如果获取到，返回true,否则返回false
     *  注意: 解锁前请确定获取到锁
     * @param lockName
     * @return
     */
    public static boolean tryLock(String lockName)  {
        RLock redisLock = redisLockMap.get(lockName);
        if (Objects.isNull(redisLock)) {
            redisLock = redissonClient.getLock(lockName);
        }
        boolean b = redisLock.tryLock();
        return b;
    }

    /**
     * 尝试获取锁，最多等待waitTime秒，如果获取到，返回true,否则返回false
     *  注意: 解锁前请确定获取到锁
     * @param lockName
     * @return
     */
    public static boolean tryLock(String lockName,Long waitTime) throws InterruptedException {
        RLock redisLock = redisLockMap.get(lockName);
        if (Objects.isNull(redisLock)) {
            redisLock = redissonClient.getLock(lockName);
        }
        boolean b = redisLock.tryLock(waitTime,TimeUnit.SECONDS);
        return b;

    }

    /**
     *  尝试获取锁，最多阻塞waitTime秒，如果没获取到，返回false，如果获取到，返回true，且最多leaseTime秒后自动解锁
     *  注意: 解锁前请确定获取到锁
     * @param lockName
     * @param waitTime
     * @param leaseTime
     * @return
     * @throws InterruptedException
     */
    public static boolean tryLock(String lockName, Long waitTime, Long leaseTime) throws InterruptedException {
        if (Objects.isNull(waitTime)) {
            waitTime = 60L;
        }
        if (Objects.isNull(leaseTime)) {
            waitTime = 10L;
        }

        RLock redisLock = redisLockMap.get(lockName);
        if (Objects.isNull(redisLock)) {
            redisLock = redissonClient.getLock(lockName);
        }
        boolean b = redisLock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
        return b;

    }

    /**
     * 释放锁
     * @param lockName
     */
    public static void unLock(String lockName){
        RLock redisLock = redisLockMap.get(lockName);
        if (Objects.isNull(redisLock)) {
            redisLock = redissonClient.getLock(lockName);
        }
        if(redisLock.isLocked()){
            redisLock.unlock();
        }
        if(redisLock.isLocked()){
            redisLockMap.remove(lockName);
        }
    }

    public static String getLocks(){
        StringBuilder stringBuilder = new StringBuilder("当前所有的锁：");
        ConcurrentHashMap.KeySetView<String, RLock> strings = redisLockMap.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            stringBuilder.append(next).append(",");
        }
        return stringBuilder.toString();

    }

}
