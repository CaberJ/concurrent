package cn.caber.concurrent.controller;

import cn.caber.concurrent.callable.CallableTask;
import cn.caber.concurrent.callable.CallableTestString;
import cn.caber.concurrent.utils.SingleThreadPoolUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description:
 * @Author: zhaikaibo
 * @Date: 2019/7/10 14:51
 */

@RestController
public class CallableTestController {

    @RequestMapping("/exe")
    public String exe() throws InterruptedException {
        int a = 10;

        ThreadPoolExecutor threadPoolExecutor = SingleThreadPoolUtil.getThreadPoolExecutor();
//        CountDownLatch countDownLatch = new CountDownLatch(10);
//        List<Future<String>> futures = new ArrayList<>();
        ArrayList<CallableTestString> callables = new ArrayList<>();

        /*for (int i = 0; i < 10; i++) {
            Future<String> submit = threadPoolExecutor.submit(new CallableTask(i,new ConcurrentHashMap()));
            futures.add(submit);
        }*/

        for (int i = 0; i < 10; i++) {
            CallableTestString callableTestString = new CallableTestString(i,"a="+a);
            callables.add(callableTestString);
        }
        List<Future<String>> futures = threadPoolExecutor.invokeAll(callables);
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        for (Future future : futures) {

            try {
                String s = (String) future.get();
                System.out.println(s + "main");
//                countDownLatch.countDown();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                throw new RuntimeException("有线程失败，所以任务失败");

            }
        }

        System.out.println("等待main线程");
       /* try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        System.out.println("所有子线程都已经执行完毕");
        System.out.println(Thread.currentThread().getName());

//        SingleThreadPoolUtil.shutdown();
        return Thread.currentThread().getName()+"--"+threadPoolExecutor.toString();
    }
}
