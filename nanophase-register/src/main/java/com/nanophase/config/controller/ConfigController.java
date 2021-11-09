package com.nanophase.config.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope// 自动刷新Nacos配置
public class ConfigController {

//    @Value("${useLocalCache:false}")
//    private boolean useLocalCache;
//
//    @GetMapping("/get")
//    public boolean get() {
//        return useLocalCache;
//    }
}
