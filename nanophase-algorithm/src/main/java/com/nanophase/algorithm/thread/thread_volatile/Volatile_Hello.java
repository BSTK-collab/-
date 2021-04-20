package com.nanophase.algorithm.thread.thread_volatile;

/**
 * 可见性问题
 */
public class Volatile_Hello {
    // 如果是修饰了引用类型，比如一个对象，那么修改了对象内部的值是不会触发可见性的，必须对象内部的值加入Volatile才可以
    // volatile是线程的缓存之间互相保持通信，和内存的数据保持一致性 另一个功能是指令重排
    private static /*volatile*/ boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(Volatile_Hello::start,"t1").start();
        Thread.sleep(1000);
        running = false;
    }

    /*
    * 线程永远无法结束
    * 线程执行时会将数据读出来，读到内存，实际上这是对数据的拷贝
    * 从内存中拷贝一份数据到该线程的本地(或被本地缓存)，每次循环时都从本地读取
    * 不会重新去内存中读取，所以即便主内存的数据被修改，子线程也不会读到
    * 但是我们在start线程的while里面打印一句话
    * System.out.println则会线程可以关闭 因为该语句触发了本地缓存与主内存的数据同步（synchronized代码块）
    * public void println(String x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }
    *
    * 可见性是指一个线程修改了一个值其他的线程都可以知道
    * */
    public static void start() {
        System.out.println("start");
        while (running) {

        }
        System.out.println("end");
    }
}
