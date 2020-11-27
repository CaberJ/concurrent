package cn.caber.concurrent.Runable;

import lombok.SneakyThrows;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

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
        lock.acquire();
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
