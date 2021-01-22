package me.jiahao.tools.service;

public interface SendMailService {
    void sendSimpleMail(String to, String subject, String content);
}
