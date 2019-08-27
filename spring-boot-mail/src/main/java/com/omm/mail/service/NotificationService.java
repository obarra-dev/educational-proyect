package com.omm.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("barra_omar@hotmail.com");
        simpleMailMessage.setSubject("JIRA-999");
        simpleMailMessage.setText("Hi Darling \n this is a email");
        javaMailSender.send(simpleMailMessage);
    }
}
