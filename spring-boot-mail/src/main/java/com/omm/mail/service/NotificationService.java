package com.omm.mail.service;

import com.omm.mail.model.EmailTemplate;
import com.omm.mail.model.FileDocumentDTO;
import com.omm.mail.model.NotificationDTO;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.util.Base64;

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

    public void send(NotificationDTO notificationDTO) throws Exception {
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setBody("<h1>Hi $name$, <br> This is JIRA $ID$ <br> Check attachment for image!</h1>");
        emailTemplate.setSubject("JIRA-$ID$");
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(notificationDTO.getTo());
        helper.setFrom("barraomar12@gmail.com");

        String body = emailTemplate.getBody();
        String subject = emailTemplate.getSubject();
        if (notificationDTO.getTemplateParameters() != null) {
            body = UtilStringTemplate.completeAndRender(
                    "email-body", notificationDTO.getTemplateParameters());

            StringTemplate stSubject = new StringTemplate(emailTemplate.getSubject(), DefaultTemplateLexer.class);
            stSubject.setAttributes(notificationDTO.getTemplateParameters());
            subject = String.valueOf(stSubject);
        }

        helper.setText(body, true);
        helper.setSubject(subject);

        /**
        for (FileDocumentDTO fileDocumentDTO : notificationDTO.getFiles()) {
            helper.addAttachment(fileDocumentDTO.getName(),
                    new ByteArrayDataSource(
                            new ByteArrayInputStream(Base64.getDecoder().decode(fileDocumentDTO.getContent())),
                            fileDocumentDTO.getMime()));

        }
         **/

        javaMailSender.send(message);
    }

}
