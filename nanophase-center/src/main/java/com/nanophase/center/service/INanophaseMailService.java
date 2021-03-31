package com.nanophase.center.service;

/**
 * @author zhj
 * @since 2021-03-31
 * @apiNote 发送邮件相关 来自 https://www.jianshu.com/p/a7097a21b42d
 */
public interface INanophaseMailService {

    /**
     * 发送文本邮件
     *
     * @param to      收件人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    void sendSimpleMail(String subject, String content, String... to);

    /**
     * 发送html邮件
     *
     * @param to      收件人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    void sendHtmlMail(String subject, String content, String... to);

    /**
     * 发送携带附件的邮件
     *
     * @param to       收件人
     * @param subject  邮件主题
     * @param content  邮件内容
     * @param filePath 附件地址
     */
    void sendAttachmentsMail(String subject, String content, String filePath, String... to);
}
