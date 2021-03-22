package com.nanophase.common.manager;

/**
 * @author zhj
 * @since 2021-03-22
 * @apiNote 异步任务管理器 来源 ruoyi
 */
public class AsyncManager {

    // 构造器私有 单例
    private AsyncManager(){};

    private static AsyncManager asyncManager = new AsyncManager();

    public AsyncManager getInstance(){
        if (null == asyncManager){
            asyncManager = new AsyncManager();
        }
        return asyncManager;
    }
}
