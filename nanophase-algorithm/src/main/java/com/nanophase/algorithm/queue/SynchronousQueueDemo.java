package com.nanophase.algorithm.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * 该队列不存储数据，也没有大小，不能迭代
 * 插入操作时的返回必须等待另一个线程消费掉时才能返回
 * 底层结构：队列（先入先出）和堆栈（后入先出）
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        // 这两个方法调用的都是transfer
        synchronousQueue.put("");
        synchronousQueue.take();
    }


}
