package com.nanophase.algorithm.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhj
 * @date 2021-04-06
 * @apiNote interrupt；在争抢锁的时候是否会被打断 juc锁
 */
public class InterruptAndLock {
    private static final ReentrantLock lock = new ReentrantLock();

    /*
    * 执行逻辑：
    * 首先启动t1（t1加锁），在1s后启动t2，再过1s，t2设置中断标志位，这时t2waiting在lock，不会被打断
    * 在争抢锁的过程中不会被interrupt干扰
    * */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("t1 start");
            lock.lock();
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
            System.out.println("t1 stop");
        });
        thread.start();

        Thread.sleep(1000);

        Thread thread2 = new Thread(() -> {
            System.out.println("t2 start");
            /*
            * 不会被interrupt打断
            * */
//            lock.lock();
            try {
                /*
                * 使用这个锁的时候，即便在争抢锁依然可以被interrupt影响
                * */
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                //被interrupt设为标志位 执行其他逻辑...
                e.printStackTrace();
            }
//            try {
//            } finally {
//                lock.unlock();
//            }
            System.out.println("t2 stop");
        });
        thread2.start();

        Thread.sleep(1000);

        thread2.interrupt();
    }
}
