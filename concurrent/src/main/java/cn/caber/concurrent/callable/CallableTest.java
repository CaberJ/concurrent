package cn.caber.concurrent.callable;

import cn.caber.concurrent.utils.ThreadPoolUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description:
 * @Author: zhaikaibo
 * @Date: 2019/7/9 9:22
 */
public class CallableTest {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor instance = ThreadPoolUtil.getInstance();

        CountDownLatch countDownLatch = new CountDownLatch(10);
        List<Future> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Future<String> submit = instance.submit(new CallableTask(i));
            futures.add(submit);
        }

        for (Future future : futures) {
            try {
                String s = (String) future.get();
                countDownLatch.countDown();
            } catch (InterruptedException |ExecutionException e) {
                throw new RuntimeException("有线程失败，所以任务失败");
            }
        }


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("所有子线程都已经执行完毕");
        System.out.println(Thread.currentThread().getName());
    }
}
