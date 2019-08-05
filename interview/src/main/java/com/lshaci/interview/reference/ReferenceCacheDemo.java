package com.lshaci.interview.reference;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 引用缓存demo
 */
public class ReferenceCacheDemo {

    static Map<String, Reference<Object>> cache = new HashMap<>();

    /**
     * -Xms5m -Xmx5m -XX:PrintGCDetails
     */
    public static void main(String[] args) {
        String key = "o";
        set(key);
        System.out.println(get(key));
//        Runtime.getRuntime().gc();
        try {
            byte[] b = new byte[30 *1024*1024]; // 模拟堆内存溢出
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
        System.err.println(get(key));

    }

    /**
     * 获取缓存
     */
    private static Object get(String key) {
        return cache.get(key).get();
    }

    /**
     * 缓存
     */
    private static void set(String key) {
        Object o = new Object();
        // 内存足够不会被回收, 内存不够会被回收
        Reference<Object> f = new SoftReference<>(o);
        // 只要gc就会被回收
//        Reference<Object> f = new SoftReference<>(o);
        cache.put(key, f);
    }
}
