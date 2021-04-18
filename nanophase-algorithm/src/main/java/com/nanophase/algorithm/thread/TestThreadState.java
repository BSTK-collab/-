package com.nanophase.algorithm.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zhj
 * @date 2021-04-18
 * @apiNote 测试线程状态
 */
public class TestThreadState {
    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(() -> {
//            System.out.println("t1执行,状态：" + Thread.currentThread().getState());
//            for (int i = 0; i < 3; i++) {
//                try {
//                    Thread.sleep(1000);
//                    System.out.println("内容" + i);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        });
//        System.out.println("t1,start之前" + thread.getState());
//        thread.start();
//        System.out.println("t1,start之后" + thread.getState());
//        thread.join();
//        System.out.println("t1,join后" +  thread.getState());

//        ==========================================================================================
//        Thread thread2 = new Thread(() -> {
//            try {
//                // 线程2进行阻塞
//                LockSupport.park();
//                System.out.println("t2执行");
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        System.out.println("t2 start之前" + thread2.getState());// NEW
//        thread2.start();
//        // 主线程沉睡1s后解锁t2
//        Thread.sleep(1000);
//        System.out.println("t2 start之后" + thread2.getState());// WAITING
//
//        // 解锁
//        LockSupport.unpark(thread2);
//        Thread.sleep(2000);
//        System.out.println("t2 解锁之后" + thread2.getState());// TIMED_WAITING 限时等待 因为t2还在sleep

        // ==========================Block状态==================================================
        Object o = new Object();
        Thread thread3 = new Thread(() -> {
            synchronized (o) {
                System.out.println("t3执行");
            }
        });

        Thread thread4 = new Thread(() -> {
            synchronized (o) {
                System.out.println("t4执行");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread4.start();
        System.out.println("t4" + thread4.getState());
        thread3.start();
        Thread.sleep(1000);
        System.out.println("t3" + thread3.getState());// BLOCKED 只有synchronized才会阻塞状态，其他的锁都是waiting或者Time_Waiting
    }
}
