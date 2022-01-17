package com.danieltns.usecase2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Usecase2EcommApplication {

	public static void main(String[] args) {
		SpringApplication.run(Usecase2EcommApplication.class, args);
	}

}
