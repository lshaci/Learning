package com.lshaci.interview.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：synchronized和Lock有什么区别？用Lock有什么好处？
 *
 * 1.原始构成
 *      synchronized是关键字属于JVM层面，
 *          monitorenter(底层是通过monitor对象来完成，wart/notify等方法依赖于monitor对象，只有在同步块或方法中才能调用wait/notify等方法)
 *          monitorexit
 *      Lock是具体类(java.util.concurrent.locks.Lock)是api层面
 *
 * 2.使用方法
 *      synchronized 不需要用户去手动释放锁，当synchronized代码块执行完成系统会自动让线程释放对锁的占用
 *      Lock 则需要用户去手动释放锁(否则可能导致死锁的现象)，需要lock(),unlock()方法配合try/finally语句块来使用
 *
 * 3.等待是否可中断
 *      synchronized  不可中断，除非抛出异常或正常运行完成
 *      ReentrantLock 可中断，1.设置超时方法tryLock(long timeout, TimeUnit unit)
 *                 2.lockInterruptibly()放代码块中，调用interrupt()方法可中断
 *
 * 4.加锁是否公平
 *      synchronized  非公平锁
 *      ReentrantLock 两者都可以，默认为非公平锁
 *
 * 5.绑定多个条件Condition
 *      synchronized 没有
 *      Lock 用来实现分组唤醒需要唤醒的线程，可以精确唤醒
 */

/**
 * 题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下：
 * A打印5次，B打印10次，C打印15次
 * 紧接着
 * A打印5次，B打印10次，C打印15次
 * 。。。。。
 * 打印10轮
 */
public class SyncAndReentrantLockDemo {

    public static void main(String[] args) throws InterruptedException {
        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print5();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print10();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print15();
            }
        }, "C").start();
    }
}

class ShareResource {
    private int number = 1;//A:1.B:2,C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            //1判断
            while (number != 1) {
                c1.await();
            }
            //2干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3通知
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            //1判断
            while (number != 2) {
                c2.await();
            }
            //2干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3通知
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            //1判断
            while (number != 3) {
                c3.await();
            }
            //2干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3通知
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

