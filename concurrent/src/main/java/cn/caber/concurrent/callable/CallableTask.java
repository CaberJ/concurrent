package cn.caber.concurrent.callable;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: zhaikaibo
 * @Date: 2019/7/9 9:18
 */
public class CallableTask implements Callable<String> {
    private List<String> strs;

    public CallableTask(List<String> strs) {
        this.strs = strs;
    }
    private static ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public String call() throws Exception {

        reentrantLock.lock();
        String s = "";
//        synchronized (CallableTask.class) {
            int size = strs.size();

            if (size > 0) {
                s = strs.get(size - 1);
                System.out.println(Thread.currentThread().getName()+"--模拟操作数据库，最后一个是：" + s);
                strs.remove(size - 1);
            }
//        }
        reentrantLock.unlock();

        return s;
    }

}
