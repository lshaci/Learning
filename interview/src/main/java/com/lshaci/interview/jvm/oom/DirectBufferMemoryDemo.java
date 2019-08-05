package com.lshaci.interview.jvm.oom;

import sun.misc.VM;

import java.nio.ByteBuffer;

/**
 * 导致原因：
 *      写NIO程序经常使用ByteBuffer来读取或写入数据，这是一种基于通道(Channel)与缓冲区(Buffer)的I/O方式。
 *      它可以使用Native函数库直接分配堆外内存，然后通过一个存储在Java堆里面的 DirectByteBuffer 对象作为这块内存的引用进行操作。
 *      这样能在一些场景中显著提高性能，因为避免了在Java堆和Native堆中来回复制数据。
 *
 *      ByteBuffer.allocate(capability) 是分配JVM堆内存，属于GC管理范围，由于需要拷贝所以速度相对较慢
 *      ByteBuffer.allocateDirect(capability)  是分配OS本地内存(1/4)，不属于GC管理范围，由于不需要内存拷贝所以速度相对较快
 *
 *      但如果不断分配本地内存，堆内存很少使用，那么JVM就不需要执行GC，DirectByteBuffer对象就不会被回收
 *      这时候堆内存充足，但是本地内存可能已经用光，再次尝试分配本地内存就会出现OutOfMemoryError，那程序就直接崩溃了
 */
public class DirectBufferMemoryDemo {

    /**
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     */
    public static void main(String[] args) {
        System.err.println("MaxDirectMemorySize=" + VM.maxDirectMemory() / (1024.0 * 1024) + "MB");

        // 分配6MB
        ByteBuffer.allocateDirect(6 * 1024 *1024); // java.lang.OutOfMemoryError: Direct buffer memory
    }
}
