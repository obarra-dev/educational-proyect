package com.omm.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//TODO why dont app into intellij disapeare when stop it?
// TODO en general xq la ultima version de spring boot no se conecta a rabbit mq directamente? y como se deberia conectar? xq funciona zipkin si los micros no se conecta?
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
