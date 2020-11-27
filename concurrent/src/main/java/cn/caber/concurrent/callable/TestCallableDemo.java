package cn.caber.concurrent.callable;

import cn.caber.concurrent.test.TestCallable;

public class TestCallableDemo implements Runnable {

    private TestCallable testCallable;
    private String name;

    public TestCallableDemo(TestCallable testCallable, String name) {
        this.testCallable = testCallable;
        this.name = name;
    }

    @Override
    public void run() {
        testCallable.doit(name);
    }
}
