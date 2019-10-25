package cn.caber.concurrent.utils;



import org.springframework.util.StringUtils;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: zhaikaibo
 * @Date: 2019/7/10 14:02
 */
public class SingleThreadPoolUtil {

    private static String corePoolSize ;
    private static String keepAliveTime ;

    private static final ThreadPoolExecutor singleThreadPoolExecutor = new ThreadPoolExecutor(
            StringUtils.isEmpty(corePoolSize) ? 10 : Integer.valueOf(corePoolSize),
            Integer.valueOf(Integer.MAX_VALUE),
            StringUtils.isEmpty(keepAliveTime) ? 10L : Long.valueOf(keepAliveTime),
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>());


    private SingleThreadPoolUtil() {
    }

    /**
     * 获取线程工具实例
     */
    public static SingleThreadPoolUtil getInstance() {
        return new SingleThreadPoolUtil();
    }

    /**
     * 获取线程池实例
     * @return
     */
    public static ThreadPoolExecutor getThreadPoolExecutor() {
        return singleThreadPoolExecutor;
    }

    /**
     * 执行任务
     * @param task
     * @return
     */
    public static Future execute(Callable task) {
        return singleThreadPoolExecutor.submit(task);

    }

    /**
     * 关闭线程池
     * 注意：此线程池为final的，此方法慎用
     */
    public static void shutdown(){
        if(singleThreadPoolExecutor!=null||!singleThreadPoolExecutor.isShutdown()){
            singleThreadPoolExecutor.shutdown();
        }
    }


}
