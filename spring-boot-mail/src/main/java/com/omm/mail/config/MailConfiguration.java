package com.omm.mail.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {

	@Bean
	public JavaMailSender getJavaMailSenderTest() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		Properties mailProperties = new Properties();
		try {
			mailProperties.put("mail.smtp.host", null);
			mailProperties.put("mail.smtp.connectiontimeout", null);
			mailProperties.put("mail.smtp.timeout", null);
			mailProperties.put("mail.smtp.auth.required", null);
			mailProperties.put("mail.smtp.user", null);
			mailProperties.put("mail.smtp.password", null);
			mailProperties.put("mail.mime.charset", null);
			mailProperties.put("mail.smtp.port", null);
			mailProperties.put("mail.smtp.starttls.enable", null);
			mailProperties.put("mail.smtp.debug", null);
			mailProperties.put("mail.smtp.ssl.enable", null);
		} catch (Exception e) {
			e.printStackTrace();
			mailSender.setJavaMailProperties(mailProperties);
		}
		return mailSender;
	}

}
