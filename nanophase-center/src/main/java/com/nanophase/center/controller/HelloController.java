package com.nanophase.center.controller;

import com.nanophase.center.entity.Test;
import com.nanophase.center.service.HelloService;
import com.nanophase.common.annotation.ReadDB;
import com.nanophase.common.annotation.WebLog;
import com.nanophase.common.annotation.WriteDB;
import com.nanophase.common.util.NetworkUtil;
import com.nanophase.common.util.R;
import com.nanophase.feign.security.SecurityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private SecurityApi securityApi;

    @Autowired
    private HelloService helloService;

    @GetMapping
    public String hello() {
        String hello = securityApi.hello();
        return hello;
    }

    @WebLog
    @PostMapping("/h")
    public String h() {
        String str = helloService.testThreadPool();
        return str;
    }

    @ReadDB
    @GetMapping("/getAll")
    public R getAll() {
        return R.success().put("data", helloService.list());
    }

    /**
     * 测试writeDB 插入一条数据
     *
     * @param test
     * @return
     */
    @WriteDB
    @PostMapping("/insert")
    public R insert(@RequestBody Test test) {
        return R.success().put("data", helloService.save(test));
    }

    /**
     * 测试获取ip地址
     * @param request
     * @return
     */
    @GetMapping("/getIp")
    public String getIp(HttpServletRequest request){
        try {
            return NetworkUtil.getIpAddress(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}

