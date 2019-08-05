package com.lshaci.interview.reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用
 *
 * 只要GC就会被回收
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.gc();
        System.out.println("...............");

        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
