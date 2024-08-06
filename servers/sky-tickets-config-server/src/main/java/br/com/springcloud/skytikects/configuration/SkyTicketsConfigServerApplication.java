package br.com.springcloud.skytikects.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SkyTicketsConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkyTicketsConfigServerApplication.class, args);
	}

}
