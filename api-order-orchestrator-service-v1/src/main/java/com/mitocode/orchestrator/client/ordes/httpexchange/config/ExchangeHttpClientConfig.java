package com.mitocode.orchestrator.client.ordes.httpexchange.config;

import com.mitocode.orchestrator.client.ordes.httpexchange.OrderServiceV1HttpExchangeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ExchangeHttpClientConfig {

    @Bean
    public RestClient orderServiceV1HttpExchangeRestClient(RestClient.Builder restClientBuilder){
        return restClientBuilder
                .clone()
                .baseUrl("http://localhost:50010/api")
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
