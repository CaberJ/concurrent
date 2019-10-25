package cn.caber.concurrent.test;

import cn.caber.concurrent.callable.CallableTask;
import cn.caber.concurrent.utils.SingleThreadPoolUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description:
 * @Author: zhaikaibo
 * @Date: 2019/7/9 9:22
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = SingleThreadPoolUtil.getThreadPoolExecutor();
        List<Future> futures = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Future submit = threadPoolExecutor.submit(new CallableTask(i,new ConcurrentHashMap()));
            futures.add(submit);
        }

        for (Future future : futures) {
            Object o =  future.get();
            System.out.println("结果是："+o);

        }

        System.out.println("等待main线程");


        System.out.println(Thread.currentThread().getName());
    }
}
