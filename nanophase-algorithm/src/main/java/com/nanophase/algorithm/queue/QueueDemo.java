package com.nanophase.algorithm.queue;

/**
 * @author zhj
 * @date 2021-04-07
 * @apiNote 队列相关
 * Queue几乎是所有队列的总接口，Queue同样实现了Collection
 * Queue主要定义了队列的三个类操作
 * 1,新增：add队列满时抛出异常；after队列满时返回false
 * 2,查看并删除
 * 3,只查看不删除
 *
 * 队列的新增方法有多个，put，add，after
 * add时如果队列满了会抛出异常，after队列满的时候返回false，put队列满的时候会一直阻塞，一直到队列不满才会执行
 */
public class QueueDemo {
}
