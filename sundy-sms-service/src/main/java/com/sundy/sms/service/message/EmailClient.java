package com.sundy.sms.service.message;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

/**
 * @author plus
 * @description 发送邮件
 */
@Component
public class EmailClient {

    private static final Logger log = LoggerFactory.getLogger(EmailClient.class);

    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.defaultEncoding}")
    private String defaultEncoding;
    @Value("${spring.mail.port}")
    private Integer port;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String auth;
    @Value("${spring.mail.properties.mail.smtp.socketFactory.class}")
    private String classInfo;
    @Value("${spring.mail.properties.mail.smtp.socketFactory.fallback}")
    private String fallback;
    @Value("${spring.mail.properties.mail.smtp.socketFactory.port}")
    private String sfport;
    @Value("${spring.mail.properties.mail.smtp.ssl.enable}")
    private String sslEnable;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @PostConstruct
    public void setProperties() {
        JavaMailSenderImpl mailSender = (JavaMailSenderImpl) javaMailSender;
        mailSender.setHost(host);
        mailSender.setPassword(password);
        mailSender.setUsername(username);
        mailSender.setDefaultEncoding(defaultEncoding);
        mailSender.setPort(port);
        Properties props = mailSender.getJavaMailProperties();
        props.setProperty("mail.smtp.auth", auth);
        props.setProperty("mail.smtp.socketFactory.class", classInfo);
        props.setProperty("mail.smtp.socketFactory.fallback", fallback);
        props.setProperty("mail.smtp.socketFactory.port", sfport);
        props.setProperty("mail.smtp.ssl.enable", sslEnable);
    }

    /**
     * 发送邮件
     */
    public void send(String sendTo, String sendFrom, String title, String content) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(sendFrom);
        messageHelper.setTo(sendTo);
        messageHelper.setSubject(title);
        messageHelper.setText(content);
        javaMailSender.send(message);
    }

    public void sendHtml(String sendTo, String sendFrom, String title, String content) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sendFrom);
        helper.setTo(sendTo);
        helper.setSubject(title);
        helper.setText(content, true);
        javaMailSender.send(message);
        log.info("EmailClient.sendHtml toEmail: {} code: {}", sendTo, content);
    }

    /**
     * 发送Thymeleaf模板邮件
     */
    public void sendThymeleaf(String sendTo, String sendFrom, String title, String template, Map<String, Object> params) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sendFrom);
        helper.setTo(sendTo);
        helper.setSubject(title);
        Context context = new Context();
//        context.setVariable("email", sendTo);
        context.setVariable("title", title);
        params.forEach((k, v) -> context.setVariable(k, v.toString()));
        String text = templateEngine.process(template, context);
        helper.setText(text, true);
        javaMailSender.send(message);
        log.info("EmailClient.sendThymeleaf toEmail: {} code: {}", sendTo, JSON.toJSONString(params));
    }
}
