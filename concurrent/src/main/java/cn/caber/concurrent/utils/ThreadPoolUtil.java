package cn.caber.concurrent.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zhaikaibo
 * @Date: 2019/7/9 9:04
 */
public class ThreadPoolUtil {


    private static Integer corePoolSize;
    private static Integer maximumPoolSize;
    private static Long keepAliveTime;
    private static Integer blockQueueSize;


    /**
     * 获得线程池实例
     * @return
     */
    public static ThreadPoolExecutor getInstance() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize == null ? 10 : corePoolSize,
                maximumPoolSize == null ? Integer.valueOf(Integer.MAX_VALUE) : maximumPoolSize,
                keepAliveTime == null ? 10L : keepAliveTime,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue(blockQueueSize == null ? 100 : blockQueueSize)
        );
        return executor;
    }


}
