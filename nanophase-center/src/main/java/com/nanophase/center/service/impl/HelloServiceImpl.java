package com.nanophase.center.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nanophase.center.entity.Test;
import com.nanophase.center.mapper.HelloMapper;
import com.nanophase.center.service.HelloService;
import com.nanophase.common.manager.AsyncManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;

@Service
public class HelloServiceImpl extends ServiceImpl<HelloMapper,Test> implements HelloService  {

    // by name注入
    @Resource(name = "consumerQueueThreadPool")
    private ExecutorService executorService;

    // 也可以指定name
//    @Autowired
//    @Qualifier(value = "consumerQueueThreadPool")
//    private ExecutorService executorService;

    @Override
    public String testThreadPool() {
        System.out.println("执行逻辑。。。");

        AsyncManager.getInstance().execute(() -> {
            System.out.println("异步执行了");
        },null);

        return "ok";
    }
}
