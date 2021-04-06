package com.nanophase.algorithm.thread;

/**
 * @author zhj
 * @date 2021-04-06
 * @apiNote interrupt；打断运行
 * Object.wait(),Thread.join,Thread.sleep这些方法运行后，线程的状态会处于waiting,或者timed_waiting
 * 状态，如果此时使用interrupt进行打断，会抛出异常（InterruptedException），线程直接结束
 *
 * 如果IO阻塞时打断运行，连接会关闭，同样抛出异常ClosedByInterruptException
 */
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " begin run");
            try {
                System.out.println("child thread sleep");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("child thread stop");
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end run");
        });
        thread.start();
        Thread.sleep(1000L);
        System.out.println("主线程沉睡1s，子线程还没有运行结束，抛出异常");

        /*
        * interrupt() 打断
        * 执行逻辑
        * 调用start，child thread start
        * 主线程沉睡1s，子线程还没有运行结束，抛出异常
        * child thread stop
        * Thread-0 end run
        * */
        thread.interrupt();
    }
}
