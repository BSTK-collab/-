package com.nanophase.algorithm.thread.thread_end;

/**
 * 线程的停止方式其中之一
 */
public class Thread_Interrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                // 没有设置标志位 执行业务
            }
            // 发现interrupt 结束
            System.out.println("thread end");
        });

        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
