package com.mitecode.case6;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.Profile;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@Profile("case6")
public class DemoModeGatewayFilter implements GatewayFilter {

    private static final String X_DEMO_MODE = "X-Demo-Mode";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("Applying Demo Mode Filter");

        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                .header(X_DEMO_MODE, Boolean.TRUE.toString())
                .build();

        ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();

        return chain.filter(mutatedExchange);
    }
}
