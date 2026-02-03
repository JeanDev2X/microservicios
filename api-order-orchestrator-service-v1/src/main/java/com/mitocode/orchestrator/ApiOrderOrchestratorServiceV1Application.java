package com.mitocode.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiOrderOrchestratorServiceV1Application {

    public static void main(String[] args) {
        SpringApplication.run(ApiOrderOrchestratorServiceV1Application.class, args);
    }

}
