package com.nanophase.common.manager;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author zhj
 * @since 2021-03-23
 * @apiNote 线程池管理类 来自网址：https://blog.csdn.net/qq_29777207/article/details/88827370
 *
 * note about
 * ArrayBlockingQueue：          由数组结构组成的有界阻塞队列。
 * LinkedBlockingQueue：         由链表结构组成的有界阻塞队列。
 * PriorityBlockingQueue：       支持优先级排序的无界阻塞队列。
 * DealyQueue：                  使用优先级队列实现的无界阻塞队列。
 * SynchronousQueue：            不存储元素的阻塞队列。
 * LinkedTransferQueue：         由链表结构组成的无界阻塞队列。
 * LinkedBlockingDeque：         由链表结构组成的双向阻塞队列
 */
@Configuration
public class ThreadPoolManager {

    private static final int MAX_POOL_SIZE = 200;
    // 获取CPU处理器数量 JVM查询操作系统，操作系统查询硬件 返回结果不一定准确
    private static final int CORE_SIZE = Runtime.getRuntime().availableProcessors() * 2 + 1;
    private static final String THREAD_NAME = "consumer-queue-thread-%d";

    @Bean(value = "consumerQueueThreadPool")
    public ExecutorService buildConsumerQueueThreadPool(){
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(THREAD_NAME).build();

        return new ThreadPoolExecutor(CORE_SIZE, MAX_POOL_SIZE, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(MAX_POOL_SIZE),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
