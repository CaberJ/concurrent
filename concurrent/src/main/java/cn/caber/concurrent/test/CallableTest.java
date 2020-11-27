package cn.caber.concurrent.test;

import cn.caber.concurrent.callable.CallableTask;
import cn.caber.concurrent.utils.SingleThreadPoolUtil;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

import static javafx.scene.input.KeyCode.T;

/**
 * @Description:
 * @Author: zhaikaibo
 * @Date: 2019/7/9 9:22
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = SingleThreadPoolUtil.getThreadPoolExecutor();
        List<Future> futures = new ArrayList<>();
//        ArrayList<String> list = new ArrayList<>();
//        ArrayList<String> strings = new ArrayList<>();
//        List<String> strings = Collections.synchronizedList(list);

        CopyOnWriteArrayList strings = new CopyOnWriteArrayList();
        strings.add("hello");
        strings.add("I");
        strings.add("am");
        strings.add("caber");
        strings.add(",");
        strings.add("who");
        strings.add("are");
        strings.add("you");
        strings.add("?");


            while (strings.size() > 0) {
                Future submit = threadPoolExecutor.submit(new CallableTask(strings));
                futures.add(submit);
            }

        for (Future future : futures) {

            Object o = null;
            try {
                o = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println("结果是："+o);
        }

        System.out.println("等待main线程");

    }
}
