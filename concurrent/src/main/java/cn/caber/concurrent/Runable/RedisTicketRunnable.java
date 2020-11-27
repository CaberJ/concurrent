package cn.caber.concurrent.Runable;

import lombok.SneakyThrows;
import org.redisson.api.RLock;

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
        lock.lock();
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
            lock.unlock();
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
