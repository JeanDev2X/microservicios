package com.mitocode.orchestrator.client.restclient.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientBaseConfig {

    //Usamos @LoadBalanced para que esta RestClient.Builder pueda resolver nombres de servicios registrados en Eureka
    @Bean("loadBalancedRestClientBuilder")
    @LoadBalanced
    public RestClient.Builder loadBalancedRestClientBuilder() {
        System.out.println("tiempo de espera para conectarse al servidor: 2000ms, tiempo de espera para recibir respuesta del servidor: 3000ms");
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(2000); //Cuánto tiempo espero para “conectarme” al servidor.
        factory.setReadTimeout(3000); //Cuánto tiempo espero a que el servidor me responda después de conectarme.

        return RestClient.builder().requestFactory(factory);
    }

    //Usamos @Primary para que esta sea la que se inyecte por defecto al cliente de Eureka
    @Primary
    @Bean
    public RestClient.Builder cleanRestClientBuilder() {
        return RestClient.builder();
    }
}
