package com.mitecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class ApiGatewayV1Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayV1Application.class, args);
	}

}
