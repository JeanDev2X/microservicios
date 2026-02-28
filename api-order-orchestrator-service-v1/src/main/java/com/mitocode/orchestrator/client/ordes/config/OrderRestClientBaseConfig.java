package com.mitocode.orchestrator.client.ordes.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;

@Configuration
public class OrderRestClientBaseConfig {

    //usamos @LoadBalanced para indicar que este RestClient.Builder debe ser configurado para usar load balancing,
    // lo que permite resolver el nombre del servicio a través de un balanceador de carga (como Ribbon o Spring Cloud LoadBalancer)
    // en lugar de usar una URL fija, esto es necesario porque en el bean orderRestClient se configura la baseUrl con el nombre del
    // servicio "http://API-ORDER-SERVICE-V1" en lugar de una URL concreta
    @Bean("loadBalancedRestClientBuilder")//Indicamo que use el cliente con loadBalanced para resuelva por los nombres de servicio de Eureka
    @LoadBalanced
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    //usamos @Primary para indicar que este es el bean principal a usar cuando se inyecte un RestClient.Builder sin especificar el nombre del bean,
    // esto es necesario porque tenemos otro RestClient.Builder definido en esta clase con el nombre "loadBalancedRestClientBuilder"
    //Para que esta la que inyecte por defecto en el cliente Eureka.
    @Primary
    @Bean
    public RestClient.Builder cleanRestClientBuilder() {
        return RestClient.builder();
    }

//    @Bean
//    public RestClient.Builder restClientBuilder() {
//        return RestClient.builder();
//    }

}
