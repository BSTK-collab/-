package com.nanophase.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    /**
     * 声明被sentinel管理
     * @return
     */
    @SentinelResource(value = "a", blockHandler = "back")
    public String getString() {
        return "1";
    }

    public String back (BlockException e) {
        return "降级了";
    }
}
