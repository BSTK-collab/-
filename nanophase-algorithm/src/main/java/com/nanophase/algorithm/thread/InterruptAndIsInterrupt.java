package com.nanophase.algorithm.thread;

/**
 * @author zhj
 * @date 2021-04-18
 * @apiNote 通过设置线程标志位结束线程
 */
public class InterruptAndIsInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    while (true) {
                        // 如果当前线程设置了停止标志位 结束循环
                        if (Thread.currentThread().isInterrupted()) {
                            System.out.println(Thread.currentThread().getName() + ":interrupt");
                            break;
                        }
                    }
                }
        );
        thread.start();
        System.out.println("thread start");
        thread.interrupt();
    }
}
