package com.mitecode.case7;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;

@Profile("case7")
@Configuration
public class Case7Config {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient authServiceWebClient(WebClient.Builder builder) {
        return builder.clone()
                .baseUrl("http://api-auth-service-v1")
                .build();
    }

}
