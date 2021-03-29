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

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

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
     *
     * @param request
     * @return
     */
    @GetMapping("/getIp")
    public String getIp(HttpServletRequest request) {
        return NetworkUtil.getIpAddress(request);
    }

    //text/html;charset=UTF-8
    public static void main(String[] args) {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", "tydyiu@qq.com");   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        // 创建一封邮件
        Session instance = Session.getInstance(new Properties());
        MimeMessage msg = new MimeMessage(instance);

        try{
            // 发件人
            msg.setFrom(new InternetAddress("tydyiu@qq.com","center","UTF-8"));

            // 收件人
            // MimeMessage.RecipientType.CC --- 抄送
            // MimeMessage.RecipientType.BCC --- 密送
            msg.setRecipient(MimeMessage.RecipientType.TO,
                    new InternetAddress("tydyiu@qq.com","ceshi2","UTF-8"));

            // 邮件主题
            msg.setSubject("ceshi","utf-8");

            // 邮件内容
            msg.setContent("what this","text/html;charset=UTF-8");

            msg.setText("this what");
            // 发件时间
            msg.setSentDate(new Date());

            // 保存设置
            msg.saveChanges();

            Transport transport = instance.getTransport();
            transport.connect("tydyiu@qq.com","password");
            // 发送邮件 getAllRecipients 添加所有收件人 抄送人等
            transport.sendMessage(msg,msg.getAllRecipients());
            transport.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

