package com.mitocode.orchestrator.client.ordes.restclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class OrderRestClientConfig {

    /*@Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }*/

    @Bean
    public RestClient orderRestClient(RestClient.Builder restClientBuilder){
        return restClientBuilder.clone().baseUrl("http://localhost:50010/api/v1").build();
    }

}
