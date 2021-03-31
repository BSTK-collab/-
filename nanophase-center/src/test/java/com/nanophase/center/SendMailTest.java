package com.nanophase.center;

import com.nanophase.center.service.INanophaseMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendMailTest {

    @Autowired
    private INanophaseMailService iNanophaseMailService;

    @Test
    public void test1() {
        String subject = "测试文本邮件发送";
        String to = "tydyiu@qq.com";
        String content = "测试文本邮件发送";
        iNanophaseMailService.sendSimpleMail(subject,content,to);
    }
}
