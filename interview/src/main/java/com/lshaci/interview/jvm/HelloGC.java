package com.lshaci.interview.jvm;

import java.util.concurrent.TimeUnit;

/**
 * jps -l 查看java进程
 * jinfo -flag PrintGCDetails [进程号]  查看是否有开启PrintGCDetails
 * jinfo -flag MetaspaceSize [进程号]
 * jinfo -flags [进程号]
 * jstat -gcutil [进程号]
 *
 * java -XX:+PrintFlagsInitial 查看jvm初始参数
 * java -XX:+PrintFlagsFinal 查看jvm修改后的参数
 * java -XX:+PrintCommandLineFlags -version
 */
public class HelloGC {

    public static int oneAddone(int x, int y) {
        return x + y;
    }

    public static void main(String[] args) throws InterruptedException {
        System.err.println("**************hello gc");

//        byte[] arr = new byte[20 * 1024 * 1024];

        TimeUnit.DAYS.sleep(1);
//
//        int res = oneAddone(1, 1);
//        System.out.println(res);
    }
}
