package cn.caber.concurrent.callable;

import java.util.concurrent.Callable;

/**
 * @Description:
 * @Author: zhaikaibo
 * @Date: 2019/10/25 9:50
 */
public class CallableTestString implements Callable<String> {
    private int num;
    private String str;

    public CallableTestString(int num, String str) {
        this.num = num;
        this.str = str;
    }

    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName()+"---"+str;
    }
}
