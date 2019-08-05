package com.lshaci.interview.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * Java8之后的版本使用Metaspace来替代永久代
 * Metaspace是方法区在HotSpot中的实现，它与持久带最大的区别在于：Metaspace并不在虚拟机内存中而是使用本地内存，
 * 也即在java8中，class metaspace（the virtual machines internal presentation of java class）,被存储在叫做Metaspace的native memory
 * <p>
 * 永久代（Metaspace）存放以下信息：
 * 虚拟机加载的类信息
 * 常量池
 * 静态变量
 * 即时编译后的代码
 */
public class MetaspaceOOMDemo {

    static class OOMTest {

    }

    /**
     * -XX:+PrintGCDetails -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
     */
    public static void main(String[] args) {
        int i = 0;//模拟多少次后发生异常

        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invoke(o, objects));

                enhancer.create();
            }
        } catch (Throwable e) {
            System.out.println("********多少次后发生了异常：" + i);
            e.printStackTrace(); // java.lang.OutOfMemoryError: Metaspace
        }
    }
}
