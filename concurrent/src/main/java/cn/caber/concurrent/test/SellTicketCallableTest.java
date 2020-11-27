package cn.caber.concurrent.test;

import cn.caber.concurrent.callable.SellTicketTask;
import cn.caber.concurrent.utils.SingleThreadPoolUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description:
 * @Author: zhaikaibo
 * @Date: 2019/10/25 9:58
 */
public class SellTicketCallableTest {

    static Integer initTicketNum = 100;

    public static void main(String[] args) {


        Integer nowTicketNum = null;

        ThreadPoolExecutor threadPoolExecutor = SingleThreadPoolUtil.getThreadPoolExecutor();

        if (nowTicketNum == null) {
            nowTicketNum = initTicketNum;
        }

        SellTicketTask sellTicketTask = new SellTicketTask(nowTicketNum);
        while (nowTicketNum > 0) {
            Future<Integer> submit = threadPoolExecutor.submit(sellTicketTask);
            Integer s = null;
            try {
                s = submit.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            nowTicketNum = s;
            System.out.println("当前剩余；" + nowTicketNum + "张票。");
        }

        System.out.println("卖完了，当前剩余；" + nowTicketNum + "张票。");


    }
}
