package com.nanophase.common.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author zhj
 * @apiNote 异步任务管理器 来源 ruoyi
 * @since 2021-03-22
 */
public class AsyncManager {

    private static final Logger logger = LoggerFactory.getLogger(AsyncManager.class);

    /**
     * 核心线程数 即使线程池处于空闲状态也会保持的数量 除非设置了allowCoreThreadTimeOut
     * 核心线程数设置标准：
     * CPU密集型：核心线程数=CPU核心数 或者CPU核数 + 1
     * IO密集型：核心线程数=2*CPU核心数
     * 混合型：核心线程数=(线程等待时间 / 线程CPU时间 + 1) * CPU核心数
     */
    private static final int CORE_POOL_SIZE = 3;

    // 构造器私有 单例
    private AsyncManager() {}

    private static AsyncManager INSTANCE = new AsyncManager();

    public static AsyncManager getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new AsyncManager();
        }
        return INSTANCE;
    }

    // 如果30秒没有可执行的任务 清空线程 防止占用资源
    private static final int KEEP_ALIVE_TIME = 30;

    // 操作延迟2000毫秒
    private static final Integer OPERATE_DELAY_TIME = 2000;

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(CORE_POOL_SIZE);

    public void execute(final Runnable run, Integer timeOut) {
        if (null == timeOut) {
             timeOut = OPERATE_DELAY_TIME;
        }
        scheduledExecutorService.schedule(run, timeOut, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        // 平滑关闭 停止接收新任务 并尝试执行完已经提交的任务
        scheduledExecutorService.shutdown();
        try {
            boolean awaitTermination = scheduledExecutorService.awaitTermination(120, TimeUnit.SECONDS);
            // 提交的任务中 如果超时 则调用shutDownNow，停止所有正在执行的任务，返回未执行完毕的任务list
            if (!awaitTermination) {
                List<Runnable> runnables = scheduledExecutorService.shutdownNow();
                logger.info("线程池执行超时，强制关闭所有任务====>{}", runnables.toString());
            }
        } catch (InterruptedException e) {
            scheduledExecutorService.shutdownNow();
            // 通知线程该中断了，如果此时线程被阻塞，则抛出InterruptedException异常 并中断阻塞
            Thread.currentThread().interrupt();
        }
    }
}
