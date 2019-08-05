package com.lshaci.interview.reference;

import java.lang.ref.SoftReference;

/**
 * 内存够用的时候就保留，不够用就回收
 */
public class SoftReferenceDemo {

    /**
     * 内存够用，软引用不会被回收
     */
    public static void softRef_Memory_Enough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    }

    /**
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     * 设置最大堆内存为5M，内存不够用，软引用会被回收
     */
    public static void softRef_Memory_NotEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    public static void main(String[] args) {
        softRef_Memory_Enough();
//        softRef_Memory_NotEnough();
    }
}
