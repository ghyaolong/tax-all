package com.chinasoft.tax.controller;

import com.chinasoft.tax.component.MailComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/email")
@RestController
public class EmailController {

    @Autowired
    private MailComponent mailComponent;

    @PostMapping("/send")
    public void sendSimpleMail() {
        /*SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(Sender);
        message.setTo("69057739@qq.com"); //自己给自己发送邮件
        message.setSubject("系统提醒");
        message.setText("请处理代办的任务");
        mailSender.send(message);*/
        mailComponent.sendEmail("69057739@qq.com","系统提醒","请处理代办的任务");
    }

}
