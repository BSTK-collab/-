package com.nanophase.algorithm.threadpool;

import java.util.concurrent.*;

/**
 * @author zhj
 * @date 2021-04-07
 * @apiNote 线程池demo
 *
 * FutureTask的父级接口
 * public interface RunnableFuture<V> extends Runnable, Future<V>   void run();
 * 最大的目的是让Future可以对Runnable进行管理，可以取消Runnable或查看是否完成等
 * 所以FutureTask实现RunnableFuture后，具备了对任务进行管理的能力(Future具备)，同时FutureTask本身也是Runnable
 *
 * FutureTask定义属性:
 *     private static final int NEW          = 0; 线程任务创建了
 *     private static final int COMPLETING   = 1; 任务正在执行
 *     private static final int NORMAL       = 2; 任务执行结束
 *     private static final int EXCEPTIONAL  = 3; 任务异常
 *     private static final int CANCELLED    = 4; 任务取消
 *     private static final int INTERRUPTING = 5; 任务打断中
 *     private static final int INTERRUPTED  = 6; 任务打断
 * FutureTask的两个构造器入参分别为Callable，Runnable，但是Runnable被适配成了Callable；可能是因为Callable具有返回值
 * FutureTask是如何将Runnable适配成Callable的LinkedBlockingQueue ？
 * 首先需要建立RunnableAdapter适配器，由于转换为Callable，适配器需要实现Callable
 * 在Callable中call方法里调用适配对象(Runnable)的方法
 */
public class ThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 1,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());
        // 构造入参为callable的线程任务 futureTask是对Callable的包装
        // Callable(接口)的返回值为泛型 V call() throws Exception;
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("子线程陷入沉睡");
            Thread.sleep(1000);
            return "子线程" + Thread.currentThread().getName() + "返回结果";
        });
        // 将任务扔给线程池执行
        executor.submit(futureTask);
        /*
        * 获取线程执行结果；
        * Future接口相关：
        * get()属于接口 Future<V> 封装的，类注释说明get方法会一直阻塞到异步任务计算完成
        * 1，V get() throws InterruptedException, ExecutionException;
        * 如果任务被取消，抛出CancellationException异常
        * 如果任务在执行时被打断，抛出InterruptedException
        * 在异步执行完毕之前，可以使用cancel方法取消
        * cancel方法标明：如果任务已经完成，是无法取消的，会直接返回true
        * 如果任务还没有开始，可以直接取消
        * 如果任务已经开始，我们给定参数mayInterruptIfRunning = true;则可以打断运行的线程；为false时则不打断线程执行
        * 2， boolean isCancelled();
        * 如果线程取消，返回true；如果线程运行结束，isDone()和isCancelled()都会返回true
        * 3，V get(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException;
        * 如果在过期时间到达后，仍没有返回，抛出TimeoutException
        * */
        String result = futureTask.get();
        System.out.println("result ---> " + result);
    }
}
