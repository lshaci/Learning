package com.lshaci.interview.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue没有容量。
 * 与其它的BlockingQueue不同，SynchronousQueue是一个不存储元素的BlockingQueue。
 * 每一个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new SynchronousQueue<>();
        new Thread(() -> {
            System.err.println(Thread.currentThread().getName() + "==>准备开始put");
            try {
                System.err.println(Thread.currentThread().getName() + "\t put 1");
                queue.put("1");
                System.err.println(Thread.currentThread().getName() + "\t put 2");
                queue.put("2");
                System.err.println(Thread.currentThread().getName() + "\t put 3");
                queue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.err.println(Thread.currentThread().getName() + "==>" + queue.take());
                TimeUnit.SECONDS.sleep(5);
                System.err.println(Thread.currentThread().getName() + "==>" + queue.take());
                TimeUnit.SECONDS.sleep(5);
                System.err.println(Thread.currentThread().getName() + "==>" + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();


    }
}
