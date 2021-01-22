package me.jiahao.tools.service.impl;

import lombok.RequiredArgsConstructor;
import me.jiahao.tools.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Component
public class SendMailServiceImpl implements SendMailService {

    @Value("${mail.fromMail.addr}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    /**
     *
     * @param to 发送给
     * @param subject 主题
     * @param content 内容
     *
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setCc(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}
