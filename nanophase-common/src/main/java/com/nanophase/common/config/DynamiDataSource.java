package com.nanophase.common.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author zhj
 * @date 2021-03-08
 * 集成Spring的AbstractRoutingDataSource实现动态数据切换
 */
public class DynamiDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getRoutingKey();
    }
}
