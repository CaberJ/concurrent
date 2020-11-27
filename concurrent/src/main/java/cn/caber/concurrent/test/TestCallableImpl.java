package cn.caber.concurrent.test;

import cn.caber.concurrent.callable.TestCallableDemo;
import cn.caber.concurrent.utils.SingleThreadPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;

@Service
public class TestCallableImpl implements TestCallable {

    @Autowired
    private Executor threadPoolTaskExecutor;

    @Override
    public void doit(String name) {
        System.out.println("我是："+name+"-"+Thread.currentThread().getName());
    }
    @Override
    public void test(String name) {
        System.out.println("主线程"+Thread.currentThread().getName());
        TestCallableDemo task = new TestCallableDemo(this, name);
//        SingleThreadPoolUtil.getThreadPoolExecutor().submit(task);
        threadPoolTaskExecutor.execute(task);
    }
}
