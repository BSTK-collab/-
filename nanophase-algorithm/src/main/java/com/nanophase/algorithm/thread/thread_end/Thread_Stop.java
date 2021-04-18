package com.nanophase.algorithm.thread.thread_end;

/**
 * 线程的停止方式其中之一
 */
public class Thread_Stop {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() ->{
            while (true) {
                try {
                    System.out.println("...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 运行子线程
        thread.start();
        // 主线程沉睡5s后结束子线程
        Thread.sleep(5000);
        // 不管线程处于什么状态 直接关闭 释放所有锁后直接关闭，可能和其他程序缺少对接导致数据一致性问题
        thread.stop();

//        thread.suspend();// 暂停 过程中锁不会被释放

//        Thread.sleep(1000);
//        thread.resume();// 继续
    }
}
