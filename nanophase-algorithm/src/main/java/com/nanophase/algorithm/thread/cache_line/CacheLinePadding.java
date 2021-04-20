package com.nanophase.algorithm.thread.cache_line;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhj
 * @date 2021-04-06
 * @apiNote 缓存行
 */
public class CacheLinePadding {
    public static long count = 10_0000_0000L;

    private static class T {
//        private long p1, p2, p3, p4, p5, p6, p7;
        public long i = 0L;
//        private long p9, p10, p11, p12, p13, p14, p15;
    }

    public static T[] arr = new T[2];

    static {
        // 可能会在一个缓存行中，存在两个cpu的内存中，需要保持缓存一致性
        // 一个缓存行的大小是64字节，一个long是8字节，加入这个long变量后，超过一个缓存行的容量
        // 为了应对缓存行大小可能会变的情况，JDK1.8引入注解@Contended 需要JVM参数-XX:-RestrictContended 只有1.8有
        // 缓存行大小 = 64b是空间与时间的效率的折中 目前多用64
        // 为什么加上了p1 ~ p7 p9 ~ p15的效率会高，因为大概率不在一个缓存行中，省了同步或互通信的步骤
        // 和volatile无关 只是缓存行的概念
        // 程序在前后没有依赖关系时，容易指令乱序执行
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        Thread thread = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                arr[0].i = i;
            }
            latch.countDown();
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                arr[0].i = i;
            }
            latch.countDown();
        });

        long start = System.nanoTime();
        thread.start();
        thread2.start();
        latch.await();
        System.out.println(System.nanoTime() - start);
    }
}
