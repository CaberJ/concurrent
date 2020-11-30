package cn.caber.concurrent.Runable;

import lombok.SneakyThrows;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class RedisClientTicketRunnable implements Runnable {

    private Integer ticketNum;

    private RedissonClient redissonClient;

    public RedisClientTicketRunnable(Integer ticketNum, RedissonClient redissonClient) {
        this.ticketNum = ticketNum;
        this.redissonClient = redissonClient;
    }

    @SneakyThrows
    @Override
    public void run() {
        // 1. 阻塞直到获取到锁
        RLock lock = redissonClient.getLock("RedisLock");
        lock.lock();

        // 2. 过期自动解锁
        // lock.lock(10, TimeUnit.SECONDS);

        // 3. 尝试加锁，最多等待3s, 10s后自动解锁
//        boolean res = lock.tryLock(3, 10, TimeUnit.SECONDS);
        try {
            while (ticketNum > 0) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticketNum--;
                System.out.println(Thread.currentThread().getName() + "还有" + ticketNum + "张票");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RLock lock1 = redissonClient.getLock("RedisLock");
            System.out.println("解锁前锁状态"+lock1.isLocked());
            if (lock1.isLocked()) {
                lock1.unlock();
            }
            System.out.println("解锁后锁状态"+lock1.isLocked());
            Thread.yield();
        }
        System.out.println("验证了只要locKName一样，就算不是同一个实例，锁的状态也是一样的");

    }

}
