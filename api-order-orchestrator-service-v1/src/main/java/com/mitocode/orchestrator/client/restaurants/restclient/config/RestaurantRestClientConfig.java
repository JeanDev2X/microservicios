package com.mitocode.orchestrator.client.restaurants.restclient.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestaurantRestClientConfig {

    @Bean
    public RestClient restaurantV1RestClient(
            @Value("${http-clients.internal.api-restaurant-service-v1.base-url}")
            String baseUrl,
            @Qualifier("loadBalancedRestClientBuilder") //Indicamos que use el cliente con LoadBalancer para resuelva por los nombres de servicio de eureka
            RestClient.Builder restClientBuilder) {
        return restClientBuilder.clone().baseUrl(baseUrl).build();
    }
}
