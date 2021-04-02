package com.nanophase.job;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhj
 * @date 2021-04-02
 * @apiNote job服务启动类
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@EnableDiscoveryClient
public class NanophaseJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(NanophaseJobApplication.class,args);
    }
}
