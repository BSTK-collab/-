package com.nanophase.center.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nanophase.center.entity.Test;
import com.nanophase.center.mapper.HelloMapper;
import com.nanophase.center.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl extends ServiceImpl<HelloMapper,Test> implements HelloService  {
}
