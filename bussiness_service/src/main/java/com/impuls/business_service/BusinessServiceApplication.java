package com.impuls.business_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//Auto configura las clases necesarias para usar Open Feign
@EnableFeignClients
public class BusinessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessServiceApplication.class, args);
	}

}
