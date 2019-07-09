package cn.caber.concurrent.callable;

import java.util.concurrent.Callable;

/**
 * @Description:
 * @Author: zhaikaibo
 * @Date: 2019/7/9 9:18
 */
public class CallableTask implements Callable<String> {
    private int num;

    public CallableTask(int num) {
        this.num = num;
    }

    @Override
    public String call() throws Exception {
        if(num==2){
            Thread.sleep(2000);
        }
        System.out.println(Thread.currentThread().getName()+"-callable-"+num);
        return Thread.currentThread().getName()+"-callable-"+num;

    }
}
