package com.lshaci.interview.jvm;

import java.util.Random;

/**
 * 1.-Xms10m -Xmx10m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC
 *      def new generation (Young区用串行) + tenured generation (Old区用串行)
 *
 * 2.-Xms10m -Xmx10m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseParNewGC
 *      par new generation (Young区用并行) + tenured generation (Old区用串行)
 *      过时: Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release
 *
 * 3.-Xms10m -Xmx10m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseParallelGC
 *      PSYoungGen (Young区用并行) + ParOldGen(Old区用并行)
 *
 * 4.(java8默认) -Xms10m -Xmx10m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseParallelOldGC
 *      PSYoungGen (Young区用并行) + ParOldGen (Old区用并行)
 *
 * 5.-Xms10m -Xmx10m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC
 *      par new generation (Young区用并行) + concurrent mark-sweep generation (Old区用CMS+串行[备用])
 *
 * 6.-Xms10m -Xmx10m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseG1GC
 *
 * 7.(已经不存在了) -Xms10m -Xmx10m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialOldGC
 */
public class GCDemo {

    public static void main(String[] args) {
        String s = "lshaci";
        while (true) {
            s += s + new Random().nextInt(999999999) + new Random().nextInt(99999999);
            s.intern();
        }
    }
}
