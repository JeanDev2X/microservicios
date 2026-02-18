package com.mitocode.orchestrator.client.restaurants.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient restaurantWebClient(
            //@Value("${http-clients.internal.api-restaurant-service-v1.base-url}")
            //String baseUrl,
            WebClient.Builder webClientBuilder) {
        String baseUrl = "http://api-restaurant-service-v1";
        return webClientBuilder
                .clone()
                .baseUrl(baseUrl)
                .build();
    }
}
