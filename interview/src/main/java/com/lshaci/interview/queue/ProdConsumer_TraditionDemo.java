package com.lshaci.interview.queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者-消费者模式(传统)
 *
 * 题目：一个初始值为零的变量，两个线程交替对其进行操作，一个加1一个减1
 *
 * 1    线程    操作    资源类
 * 2    判断    工作    通知 【资源类】
 * 3    防止虚假唤醒机制
 */
public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        // 生产
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.increment();
            }
        }, "A").start();

        // 消费
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.decrement();
                try {
                    TimeUnit.SECONDS.sleep(3); // 需要3s才能消费完成
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}

/**
 * 资源类
 */
class ShareData {

    private int number = 0; // 初始值

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    /**
     * 加一(生产)
     */
    public void increment() {
        lock.lock();
        try {
            // 1.判断(多线程判断需要使用while)
            while (number != 0) {
                // 等待, 不生产数据
                condition.await(); // 临时释放锁，被唤醒后重新获得锁
            }
            // 2.工作(生产数据)
            number++;
            System.err.println(Thread.currentThread().getName() + "==>生产数据已完成 \t number = " + number);
            // 3.通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 减一(消费)
     */
    public void decrement() {
        lock.lock();
        try {
            // 1.判断
            while (number == 0) {
                // 等待, 不消费数据
                condition.await();
            }
            // 2.工作(消费数据)
            number--;
            System.err.println(Thread.currentThread().getName() + "==>消费数据已完成 \t number = " + number);
            // 3.通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
