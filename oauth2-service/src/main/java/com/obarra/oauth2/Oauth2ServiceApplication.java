package com.obarra.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableFeignClients
@SpringBootApplication
public class Oauth2ServiceApplication {

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("12345"));
		System.out.println(new BCryptPasswordEncoder().encode("12345"));
		System.out.println(new BCryptPasswordEncoder().encode("12345"));
		System.out.println(new BCryptPasswordEncoder().encode("12345"));
		
		SpringApplication.run(Oauth2ServiceApplication.class, args);
	}

}
