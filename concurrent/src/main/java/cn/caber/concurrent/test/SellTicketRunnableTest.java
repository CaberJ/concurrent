package cn.caber.concurrent.test;

import cn.caber.concurrent.Runable.SellTicketRunnerTask;
import cn.caber.concurrent.callable.SellTicketTask;
import cn.caber.concurrent.utils.SingleThreadPoolUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description:
 * @Author: zhaikaibo
 * @Date: 2019/10/25 10:25
 */
public class SellTicketRunnableTest {

    public static void main(String[] args) {

        int initTicketNum = 100;
        ThreadPoolExecutor threadPoolExecutor = SingleThreadPoolUtil.getThreadPoolExecutor();

        SellTicketRunnerTask sellTicketRunnerTask = new SellTicketRunnerTask(initTicketNum);
        Thread thread1 = new Thread(sellTicketRunnerTask, "--1");
        Thread thread2 = new Thread(sellTicketRunnerTask, "--2");
        Thread thread3 = new Thread(sellTicketRunnerTask, "--3");
        Thread thread4 = new Thread(sellTicketRunnerTask, "--4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
//        System.out.println("卖完了，还剩；"+initTicketNum+"张");

    }
}
