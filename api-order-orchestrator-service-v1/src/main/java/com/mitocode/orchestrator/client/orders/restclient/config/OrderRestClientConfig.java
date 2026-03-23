package com.mitocode.orchestrator.client.orders.restclient.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class OrderRestClientConfig {

    /*@Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }*/

//    @Bean
//    public RestClient orderRestClient(
//            //@Value("${http-clients.internal.api-order-service-v1.base-url}")
//            //String baseUrl,
//            RestClient.Builder restClientBuilder){
//        String baseUrl = "http://API-ORDER-SERVICE-V1";
//        return restClientBuilder.clone().baseUrl(baseUrl).build();
//    }
//40

//    @Bean
//    public RestClient orderRestClient(
//            @Value("${http-clients.internal.api-order-service-v1.base-url}")
//            String baseUrl,
//            RestClient.Builder restClientBuilder){
//        return restClientBuilder.clone().baseUrl(baseUrl).build();
//    }

    @Bean
    public RestClient orderRestClient(
            @Value("${http-clients.internal.api-order-service-v1.base-url}")
            String baseUrl,
            @Qualifier("loadBalancedRestClientBuilder")//Indicamo que use el cliente con loadBalanced para resuelva por los nombres de servicio de Eureka
            RestClient.Builder restClientBuilder){
        return restClientBuilder.clone().baseUrl(baseUrl).build();
    }



}
