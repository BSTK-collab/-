package com.nanophase.algorithm.thread.ordering;

import java.io.IOException;

/**
 * @author zhj
 * @date 2021-04-20
 * @apiNote 对象逃逸
 */
public class ThisEscape {
    private int num = 10;

    Thread thread = null;
    public ThisEscape() {
        thread = new Thread(() -> {
            // 可能num在系统默认初始化为0的时候就被调用，还未赋值为10，但几率很小
            // 解决的方式是不要在构造方法中new线程并且启动，可以单独写一个方法开启线程
            System.out.println(num);
        });
    }

    public void start() {
        thread.start();
    }

    public static void main(String[] args) throws IOException {
        new ThisEscape();
        System.in.read();
    }
}
