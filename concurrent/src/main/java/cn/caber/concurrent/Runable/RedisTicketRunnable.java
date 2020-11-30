package cn.caber.concurrent.Runable;

import lombok.SneakyThrows;
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

public class RedisTicketRunnable implements Runnable {

    private Integer ticketNum;

    private RLock lock;

    public RedisTicketRunnable(Integer ticketNum, RLock lock) {
        this.ticketNum = ticketNum;
        this.lock = lock;
    }

    @SneakyThrows
    @Override
    public void run() {
        // 1. 阻塞直到获取到锁
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
            System.out.println("解锁前锁状态"+lock.isLocked());
            if (lock.isLocked()) {
                lock.unlock();
            }
            System.out.println("解锁后锁状态"+lock.isLocked());
            Thread.yield();
        }


      /*  boolean b = lock.tryLock();
        if(b){
            try {
                while (ticketNum > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ticketNum--;
                    System.out.println(Thread.currentThread().getName() + "还有" + ticketNum + "张票");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }else {

        }*/

    }

}
