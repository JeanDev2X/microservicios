package com.mitocode.orchestrator.client.payments.restclient.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class PaymentRestClientConfig {

    @Bean
    public RestClient paymentsV1RestClient(
            @Value("${http-clients.internal.api-payment-service-v1.base-url}")
            String baseUrl,
            @Qualifier("loadBalancedRestClientBuilder")//Indicamo que use el cliente con loadBalanced para resuelva por los nombres de servicio de Eureka
            RestClient.Builder restClientBuilder){
        return restClientBuilder.clone().baseUrl(baseUrl).build();
    }

    @Bean
    public RestClient paymentsV2RestClient(
            @Value("${http-clients.internal.api-payment-service-v2.base-url}")
            String baseUrl,
            @Qualifier("loadBalancedRestClientBuilder")//Indicamo que use el cliente con loadBalanced para resuelva por los nombres de servicio de Eureka
            RestClient.Builder restClientBuilder){
        return restClientBuilder.clone().baseUrl(baseUrl).build();
    }

}
