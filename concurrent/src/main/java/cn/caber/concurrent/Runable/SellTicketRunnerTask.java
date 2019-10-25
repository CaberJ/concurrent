package cn.caber.concurrent.Runable;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: zhaikaibo
 * @Date: 2019/10/25 10:24
 */
public class SellTicketRunnerTask implements Runnable {
    private static int ticketNum = 100;

    ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {

//        synchronized (this) {
        lock.lock();
        while (ticketNum > 0) {
            ticketNum--;
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---还剩：" + ticketNum + "张票");
        }
        lock.unlock();
//        }

   /* private synchronized void sell( ) {

    }*/


    }
}
