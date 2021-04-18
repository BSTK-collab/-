package com.nanophase.algorithm.thread;

/**
 * @author zhj
 * @date 2021-04-06
 * @apiNote interrupt；设标志位 优雅的结束一个线程的方式之一
 * Object.wait(),Thread.join,Thread.sleep这些方法运行后，线程的状态会处于waiting,或者timed_waiting
 * 状态，如果此时使用interrupt进行打断，会抛出异常（InterruptedException），线程直接结束
 * 如果IO阻塞时打断运行，连接会关闭，同样抛出异常ClosedByInterruptException
 *
 * 由于线程在sleep的时候，在时间结束之前无法被唤醒，可以使用interrupt设为标志位唤醒，
 * 然后在cache中执行其他逻辑，这是一种唤醒的方式（cache到该异常时interrupt自动复位）
 */
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " begin run");
            try {
                System.out.println("child thread sleep");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("child thread interrupt");
                // cache到异常的时候 interrupt会被复位(false)
                System.out.println(Thread.currentThread().isInterrupted());
//                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end run");
        });
        thread.start();
        Thread.sleep(1000L);
        System.out.println("主线程沉睡1s，子线程还没有运行结束，抛出异常");

        /*
        * interrupt() 打断(设置标志位)
        * 执行逻辑
        * 调用start，child thread start
        * 主线程沉睡1s，子线程还没有运行结束，抛出异常
        * child thread stop
        * Thread-0 end run
        * */
        thread.interrupt();
//        System.out.println(thread.isInterrupted()); //  true
        // 查询某个线程是否被打断过
//        thread.isInterrupted();
        // 静态方法，查询线程是否被打断过，并重新设置标志位
//        Thread.interrupted()
    }
}
