package com.lshaci.interview.queue;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者-消费者模式(阻塞队列)
 *
 * 题目：一个初始值为零的变量，两个线程交替对其进行操作，一个加1一个减1
 *
 * 1    线程    操作    资源类
 * 2    判断    工作    通知 【资源类】
 * 3    防止虚假唤醒机制
 */
public class ProdConsumer_BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        ShareResource shareResource = new ShareResource(new SynchronousQueue<>());

        // 生产
        new Thread(() -> {
            try {
                shareResource.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        // 消费
        new Thread(() -> {
            try {
                shareResource.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        TimeUnit.SECONDS.sleep(10); // 10s后停止
        System.err.println();
        shareResource.stop();
    }
}

/**
 * 资源类
 */
class ShareResource {

    private volatile boolean enable = true; // 默认开启，进行生产和消费

    private AtomicInteger atomicInteger = new AtomicInteger();

    private BlockingQueue<Integer> blockingQueue = null;

    public ShareResource(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    /**
     * 生产
     */
    public void producer() throws InterruptedException {
        int i;
        boolean offer;
        while (enable) {
            i = atomicInteger.incrementAndGet();
            offer = blockingQueue.offer(i, 2, TimeUnit.SECONDS);
            if (offer) {
                System.err.println(Thread.currentThread().getName() + "==>生产数据到队列成功, data = " + i);
            } else {
                System.err.println(Thread.currentThread().getName() + "==>生产数据到队列失败, data = " + i);
            }

            TimeUnit.SECONDS.sleep(1); // 1s生产一条数据
        }
        System.err.println(Thread.currentThread().getName() + "==>结束生产数据");
    }

    /**
     * 消费
     */
    public void consumer() throws InterruptedException {
        Integer data;
        while (enable) {
            data = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (Objects.isNull(data)) {
                // 如果2s没有取到数据则停止
                System.err.println(Thread.currentThread().getName() + "==>2s未消费到数据");
                continue;
            }
            System.err.println(Thread.currentThread().getName() + "==>从队列中消费到数据, data = " + data);
        }
        System.err.println(Thread.currentThread().getName() + "==>结束消费数据");
    }

    /**
     * 停止
     */
    public void stop() {
        this.enable = false;
    }
}
