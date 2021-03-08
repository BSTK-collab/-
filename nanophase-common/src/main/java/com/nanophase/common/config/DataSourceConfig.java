package com.nanophase.common.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.nanophase.common.constant.CenterConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhj
 * @date 2021-03-08
 * 使用Druid配置多数据源
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "master")
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource dataSourceMaster(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "slave")
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource dataSourceSlave(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamiDataSource dataSource(){
        Map<Object, Object> map = new HashMap<>();
        map.put(CenterConstant.db.MASTER,dataSourceMaster());
        map.put(CenterConstant.db.SLAVE,dataSourceSlave());
        DynamiDataSource dynamiDataSource = new DynamiDataSource();
        // 默认数据源 master
        dynamiDataSource.setDefaultTargetDataSource(dataSourceMaster());
        // 数据源列表
        dynamiDataSource.setTargetDataSources(map);
        return dynamiDataSource;
    }
}
