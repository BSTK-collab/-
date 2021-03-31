package com.nanophase.center.controller;

import com.nanophase.center.service.INanophaseMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhj
 * @since 2021-03-31
 * @apiNote 发送邮件控制器
 */
@RestController
@RequestMapping("/mail")
public class NanophaseMailController {

    @Autowired
    private INanophaseMailService iNanophaseMailService;
}
