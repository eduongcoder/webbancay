package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.demo.repository.http")
public class WebbancayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebbancayApplication.class, args);
	}

}
