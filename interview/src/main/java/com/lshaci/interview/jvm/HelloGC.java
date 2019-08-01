package com.lshaci.interview.jvm;

import java.util.concurrent.TimeUnit;

/**
 * jps -l 查看java进程
 * jinfo -flag PrintGCDetails [进程号]  查看是否有开启PrintGCDetails
 * jinfo -flag MetaspaceSize [进程号]
 * jinfo -flags [进程号]
 * jstat -gcutil [进程号]
 */
public class HelloGC {

    public static int oneAddone(int x, int y) {
        return x + y;
    }

    public static void main(String[] args) throws InterruptedException {
        System.err.println("**************hello gc");

        TimeUnit.DAYS.sleep(1);

        int res = oneAddone(1, 1);
        System.out.println(res);
    }
}
