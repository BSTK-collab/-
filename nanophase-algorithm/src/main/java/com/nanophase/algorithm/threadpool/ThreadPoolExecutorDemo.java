package com.nanophase.algorithm.threadpool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

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

    public static void main(String[] args) throws Exception {

//        ThreadPoolExecutor executor = new ThreadPoolExecutor(
//                1, 2, 60, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(5));
        System.out.println(-1 << 29);
    }





    public void demo() throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<String>> list = new ArrayList<>();
        list.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                System.out.println(1);
                return "1";
            }
        });
        list.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
//                Thread.sleep(1000);
                System.out.println(2);
                return null;
            }
        });
        //  任意一个任务执行完毕即可返回
        String s = executorService.invokeAny(list);
//        System.out.println(s);
    }
}
