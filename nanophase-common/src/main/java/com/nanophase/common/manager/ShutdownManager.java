package com.nanophase.common.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author zhj
 * @since 2021-03-23
 * @apiNote 关闭线程池资源
 */
@Component
public class ShutdownManager {

    private static final Logger logger = LoggerFactory.getLogger(ShutdownManager.class);

    @PreDestroy
    public void shutdownAsyncManager(){
        logger.info("==============关闭线程池资源");
        AsyncManager.getInstance().shutdown();
    }
}
