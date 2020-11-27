package cn.caber.concurrent.Runable;


import java.util.Objects;

/**
 * 两个线程各自输出奇偶数
 */
public class PrintNumTask implements Runnable {

    private Integer curNum;
    private Integer count;
    private Object object;

    public PrintNumTask(Integer curNum, Integer count, Object object) {
        this.curNum = curNum;
        this.count = count;
        this.object = object;
    }

    @Override
    public void run() {

        while (curNum < count) {

            synchronized (object) {
                curNum++;
                System.out.println(Thread.currentThread().getName() + ":" + curNum);
                object.notify();
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
