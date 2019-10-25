package cn.caber.concurrent.callable;

import java.util.concurrent.Callable;

/**
 * @Description:
 * @Author: zhaikaibo
 * @Date: 2019/10/25 9:55
 */
public class SellTicketTask implements Callable<Integer> {

    private int ticketNum;

    public SellTicketTask(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    @Override
    public Integer call() throws Exception {
        ticketNum -=1;
        System.out.println(Thread.currentThread().getName()+"---还剩："+ticketNum+"张票");
        return ticketNum;
    }
}
