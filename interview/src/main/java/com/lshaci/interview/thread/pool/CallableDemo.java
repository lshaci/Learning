package com.lshaci.interview.thread.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());

        new Thread(futureTask, "F").start();
        new Thread(futureTask, "T").start(); // 不会再次进入call方法

        Integer r = futureTask.get(); // 会阻塞

        System.err.println("=========>结果：" + r);
    }

    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.err.println("***************call();");
            return 1024;
        }
    }
}


