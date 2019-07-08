package cn.caber.concurrent.callable;

import cn.caber.concurrent.utils.ExecutorUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class CallableTest {
    public static void main(String[] args) {
        ThreadPoolExecutor instance = ExecutorUtil.getInstance();
        Future<String> submit = instance.submit(new CallableTask());
        try {
            String s = submit.get();
        } catch (InterruptedException|ExecutionException e) {
            e.printStackTrace();
        }
    }
}
