package cn.caber.concurrent.test;

import cn.caber.concurrent.Runable.PrintNumTask;

public class PrintNumTest {

    public static void main(String[] args) {
        Object o = new Object();
        PrintNumTask printNumTask = new PrintNumTask(1, 50, o);
        Thread t1 = new Thread(printNumTask, "线程1");
        Thread t2 = new Thread(printNumTask, "线程2");
        t1.start();
        t2.start();
    }
}
