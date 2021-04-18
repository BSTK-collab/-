package com.nanophase.algorithm.thread.thread_end;

/**
 * 线程的停止方式其中之一
 */
public class Thread_Stop_VolatileFlag {
    private static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            long i = 0;
            /*
            * 如果不依赖中间的状态，这种方式可以使用
            * 如果停止状态设置为i = 偶数/奇数 or其他状态该方法无法实现
            * 或者中间存在wait等阻塞方法，即便running状态恢复，但是wait结束前仍然无法回到下次循环
            * interrupt可以满足中间存在状态的要求，但是太精确仍然无法满足
            * */
            while (running) {
                i++;
            }
            // 不太方便控制循环次数
            System.out.println(i);
        });
        thread.start();
        Thread.sleep(1000);
        running = false;
    }
}
