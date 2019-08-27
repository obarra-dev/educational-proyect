package com.omm.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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

    public void sendWithAttachment() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo("barra_omar@hotmail.com");

        helper.setSubject("JIRA-999");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);

        helper.addAttachment("black-avatar.png", new ClassPathResource("black-avatar.png"));
        helper.addAttachment("black-avatar-2.png", new ClassPathResource("black-avatar-2.png"));

        javaMailSender.send(mimeMessage);

    }
}
