package com.mitocode.orchestrator.client.orders.httpexchange.config;

import com.mitocode.orchestrator.client.orders.httpexchange.OrderServiceV1HttpExchangeClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ExchangeHttpClientConfig {

    @Bean
    public RestClient orderServiceV1HttpExchangeRestClient(
            @Value("${http-clients.internal.api-order-service-v1.base-url}")
            String baseUrl,
            @Qualifier("loadBalancedRestClientBuilder")
            RestClient.Builder restClientBuilder){
        return restClientBuilder
                .clone()
                .baseUrl(baseUrl)
                .build();
    }

    @Bean
    public OrderServiceV1HttpExchangeClient orderServiceV1HttpExchangeClient(RestClient orderServiceV1HttpExchangeRestClient){
        return HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(orderServiceV1HttpExchangeRestClient))
                .build()
                .createClient(OrderServiceV1HttpExchangeClient.class);
    }

}
