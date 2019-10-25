package cn.caber.concurrent.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: zhaikaibo
 * @Date: 2019/7/9 9:18
 */
public class CallableTask implements Callable<String> {
    private int num;
    private ConcurrentHashMap map;

    public CallableTask(int num, ConcurrentHashMap map) {
        this.num = num;
        this.map = map;
    }

    @Override
    public String call() throws Exception {

//        return map.get("stu1").toString();
        num--;
        return num+"main";
    }

}
