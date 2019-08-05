package com.lshaci.interview.jvm.oom;

import java.util.concurrent.TimeUnit;

/**
 * 高并发请求服务器时，经常会出现如下异常：java.long.OutOfMemoryError: unable to create new native thread
 * 准确的讲该native thread异常与对应的平台有关
 *
 * 导致原因：
 * 1.你的应用创建了太多线程，一个应用进程创建多个线程，超过系统承载极限。
 * 2.你的服务器并不允许你的应用程序创建这么多线程，linux系统默认允许单个进程可以创建的线程数是1024个，
 *   你的应用程序超过这个数量，就会报：java.long.OutOfMemoryError: unable to create new native thread
 *
 * 解决办法：
 * 1.想办法降低应用程序创建线程的数量，分析应用是否真的需要创建那么多线程，如果不是，改代码将线程数降到最低。
 * 2.对于有的应用，确实需要创建多个线程，远超过linux系统默认的1024个线程的限制，可以通过修改linux服务器配置，扩大linux默认限制。
 */
public class UnableCreateNewThreadDemo {

    /**
     * javac -d . UnableCreateNewThreadDemo.java
     * java com.lshaci.interview.jvm.oom.UnableCreateNewThreadDemo
     */
    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            System.err.println("============>" + i);
            new Thread(() -> {
                try {
                    TimeUnit.DAYS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
