package com.nanophase.algorithm.thread;

public class TestThread {
    static int i = 3000;
    public static void main(String[] args) {
        Thread thread = new Thread(TestThread::test);
        Thread thread1 = new Thread(TestThread::test);
        thread.start();
        thread1.start();
    }

    public static void test(){
        while (i >= 0) {
            System.out.println(i--);
        }
    }
}
