package cn.caber.concurrent.Runable;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 多线程卖票
 * @Author: zhaikaibo
 * @Date: 2019/10/25 10:24
 */
public class SellTicketRunnerTask implements Runnable {
    private Integer ticketNum ;

    public SellTicketRunnerTask(Integer ticketNum){
        this.ticketNum = ticketNum;
    }

    ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {

//        synchronized (this) {
//        lock.lock();
        while (ticketNum > 0) {
            ticketNum--;
            System.out.println(Thread.currentThread().getName() + "在卖票，还剩：" + ticketNum + "张票");
        }
//        lock.unlock();
//        }




    }
}
