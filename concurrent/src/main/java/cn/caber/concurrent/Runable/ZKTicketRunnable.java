package cn.caber.concurrent.Runable;

import lombok.SneakyThrows;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

public class ZKTicketRunnable implements Runnable {

    private int ticketNum;
    private InterProcessMutex lock;

    public ZKTicketRunnable(int ticketNum, InterProcessMutex lock) {
        this.ticketNum = ticketNum;
        this.lock = lock;
    }


    @SneakyThrows
    @Override
    public void run() {
        // 获取不到锁会一直阻塞
        lock.acquire();
        //获取锁时最多等待100s  如果100s还没获取到锁，则获取锁失败
        // boolean acquire = lock.acquire(100, TimeUnit.SECONDS);
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
            lock.release();
            Thread.yield();
        }
    }


}
