package com.nanophase.center.service.impl;

import com.nanophase.center.service.INanophaseMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author zhj
 * @since 2021-03-31
 * @apiNote 发送邮件相关 来自 https://www.jianshu.com/p/a7097a21b42d
 */
@Slf4j
@Service
public class INanophaseMailServiceImpl implements INanophaseMailService {

    /**
     * SpringBoot提供的发送邮件的简单抽象
     */
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String from;

    /**
     * 发送文本邮件
     *
     * @param to      收件人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    @Override
    public void sendSimpleMail(String subject, String content, String... to) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 邮件发送人
        simpleMailMessage.setFrom(from);
        // 邮件主题
        simpleMailMessage.setSubject(subject);
        // 收件人
        simpleMailMessage.setTo(to);
        // 邮件内容
        simpleMailMessage.setText(content);
        // 发送邮件
        mailSender.send(simpleMailMessage);
        log.info("普通文本邮件已发送");
    }


    /**
     * 发送html邮件
     *
     * @param to      收件人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    @Override
    public void sendHtmlMail(String subject, String content, String... to) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            // 发件人
            messageHelper.setFrom(from);
            // 收件人
            messageHelper.setTo(to);
            // 主题
            messageHelper.setSubject(subject);
            // 内容 html格式
            messageHelper.setText(content, true);
            mailSender.send(mimeMessage);
            log.info("html邮件已发送");
        } catch (MessagingException e) {
            log.error("html邮件发送异常，异常信息:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 发送携带附件的邮件
     *
     * @param to       收件人
     * @param subject  邮件主题
     * @param content  邮件内容
     * @param filePath 附件地址
     */
    @Override
    public void sendAttachmentsMail(String subject, String content, String filePath, String... to) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            //日志信息
            log.info("携带附件邮件已发送");
        } catch (MessagingException e) {
            log.error("发送携带附件邮件时发生异常，异常信息:{}", e.getMessage());
            e.printStackTrace();
        }
    }
}
