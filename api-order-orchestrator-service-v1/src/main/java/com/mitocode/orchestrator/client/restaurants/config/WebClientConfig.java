package com.mitocode.orchestrator.client.restaurants.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient restaurantWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .clone()
                .baseUrl("http://localhost:50040/api/v1/restaurants")
                .build();
    }

}
