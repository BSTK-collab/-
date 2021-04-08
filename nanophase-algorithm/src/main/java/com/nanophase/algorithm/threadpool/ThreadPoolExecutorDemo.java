package com.nanophase.algorithm.threadpool;

/**
 * @author zhj
 * @date 2021-04-08
 * @apiNote 线程池demo
 *
 * 线程池的作用是在请求量大的时候充分使用CPU的计算速度
 * 线程池的流程：
 * 判断请求数量是否小于coreSize，如果小于并且队列不满，尝试将任务放入队列
 * 如果放入队列失败，判断线程数是否大于MaxSize，否则新增线程，true则拒绝请求
 */
public class ThreadPoolExecutorDemo {
}
