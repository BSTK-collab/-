package com.nanophase.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhj
 * @date 2021-03-08
 * 使用ThreadLocal保存当前线程操作的数据源
 */
public class DynamicDataSourceHolder {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);

    private static final ThreadLocal<String> routingKey = new ThreadLocal<>();

    // 绑定当前数据源
    public static void setRoutingKey(String key){
        logger.info("切换数据源至:{}",key);
        routingKey.set(key);
    }

    // 获取当前数据源
    public static String getRoutingKey(){
        return routingKey.get();
    }

    // 删除当前线程绑定的routingKey
    public static void deleteRoutingKey(){
        routingKey.remove();
    }
}
