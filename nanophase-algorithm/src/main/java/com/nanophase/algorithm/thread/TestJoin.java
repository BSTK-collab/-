package com.nanophase.algorithm.thread;

/**
 * @author zhj
 * @date 2021-04-06
 * @apiNote join测试
 * join的意思是指当前线程等待另一个线程执行完毕后在继续操作
 * 此方法的逻辑，如果加入了join，主方法在遇到join后，代码停止执行。一直到子线程结束后才会继续执行
 */
public class TestJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " is run");// main is run
        Thread thread1 = new Thread(() -> {
            System.out.println("begin run " + Thread.currentThread().getName()); // begin run Thread-0
            try {
                Thread.sleep(30000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end run " + Thread.currentThread().getName()); // end run Thread-0
        });
        thread1.start();

        /*
         * 如果不加入join方法主线程会直接执行完毕 流程为
         * main is run
         * Thread[main,5,main] is end
         *
         * 如果使用join方法，主线程会等待子线程30s后才会继续执行。在这期间，主线程的状态为timed-waiting
         * 执行流程
         * main is run
         * begin run Thread-0
         * end run Thread-0
         * Thread[main,5,main] is end
         * */
        thread1.join();
        System.out.println(Thread.currentThread() + " is end"); // Thread[main,5,main] is end
    }
}
