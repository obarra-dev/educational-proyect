package com.omm.mail;

import com.omm.mail.model.FileDocumentDTO;
import com.omm.mail.model.NotificationDTO;
import com.omm.mail.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringBootMailApplication implements CommandLineRunner {

	@Autowired
	private NotificationService notificationService;

	@Value("file.attach")
	private String fileAttach;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMailApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Sending Email...");

		FileDocumentDTO fileDocumentDTO = new FileDocumentDTO();
		fileDocumentDTO.setContent(fileAttach);
		fileDocumentDTO.setMime("image/png");
		fileDocumentDTO.setName("avatar.png");
		List<FileDocumentDTO> files = new ArrayList<>();
		files.add(fileDocumentDTO);
		NotificationDTO notificationDTO = new NotificationDTO();
		notificationDTO.setFiles(files);
		Map<String, Object> templateParameters = new HashMap<>();
		templateParameters.put("ID", "COPPEL-999");
		templateParameters.put("name", "Team");
		templateParameters.put("title", "Welcome To Omm");
		List<String> names = new ArrayList<>();
		names.add("Der");
		names.add("Omar");
		names.add("Nelson");
		names.add("Matias");
		templateParameters.put("friends", names);
		notificationDTO.setTemplateParameters(templateParameters);
		String[] to = {"barra_omar@hotmail.com", "nelson.benitez92@gmail.com", "acetosella90@gmail.com"};
		notificationDTO.setTo(to);
		//notificationDTO.setTemplateKey();

		notificationService.send(notificationDTO);

		System.out.println("Done");
	}
}
