package com.lshaci.interview.jvm.oom;

/**
 * 堆内存溢出
 * <p>
 * java.lang.OutOfMemoryError: Java heap space
 */
public class JavaHeapSpaceDemo {

    /**
     * -Xms1m -Xmx1m (设置堆内存大小)
     */
    public static void main(String[] args) {
        byte[] b = new byte[2 * 1024 * 1024];
    }
}
