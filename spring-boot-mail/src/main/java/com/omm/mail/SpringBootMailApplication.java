package com.omm.mail;

import com.omm.mail.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.MessagingException;
import java.io.IOException;

@SpringBootApplication
public class SpringBootMailApplication implements CommandLineRunner {

	@Autowired
	private NotificationService notificationService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMailApplication.class, args);
	}

	@Override
	public void run(String... args) throws MessagingException {
		System.out.println("Sending Email...");

		notificationService.sendWithAttachment();

		System.out.println("Done");
	}
}
