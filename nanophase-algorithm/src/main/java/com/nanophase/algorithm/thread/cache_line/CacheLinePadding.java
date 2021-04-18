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
        private long p1, p2, p3, p4, p5, p6, p7;
        public long i = 0L;
        private long p9, p10, p11, p12, p13, p14, p15;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) {
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
    }
}
