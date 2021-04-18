package com.nanophase.algorithm.thread;

/**
 * @author zhj
 * @date 2021-04-06
 * @apiNote interrupt；在争抢锁的时候是否会被打断
 */
public class InterruptAndSync {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();

        Thread thread = new Thread(() -> {
            synchronized (o) {
                try {
                    // t1拿到锁之后沉睡5s
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        // 确保t1启动
        Thread.sleep(1000);

        Thread thread2 = new Thread(() -> {
//            synchronized (o) {
//                System.out.println("t2 get lock");
//            }
        });
        thread2.start();
        // 确保t2完全启动
        Thread.sleep(1000);

        /*
        * 在线程争抢锁的时候，interrupt设置的标志位无效，在争抢锁的过程中不会被interrupt干扰
        * */
        thread2.interrupt();
    }
}
