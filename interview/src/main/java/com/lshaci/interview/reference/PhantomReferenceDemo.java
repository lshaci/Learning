package com.lshaci.interview.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用
 *
 * 被垃圾回收前会被放入引用队列ReferenceQueue
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1, referenceQueue);

        System.out.println("o1:" + o1);
        System.out.println("phantomReference.get():" + phantomReference.get());
        System.out.println("referenceQueue.poll():" + referenceQueue.poll());

        System.out.println("=================");
        o1 = null;
        System.gc();
        Thread.sleep(500);

        System.err.println("o1:" + o1);
        System.err.println("phantomReference.get():" + phantomReference.get());
        System.err.println("referenceQueue.poll():" + referenceQueue.poll());
    }
}
