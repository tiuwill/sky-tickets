package br.com.springcloud.skytickets.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SkyTicketsPaymentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkyTicketsPaymentsApplication.class, args);
	}

}
