package com.mitecode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Component
public class RequestIdFilter implements GlobalFilter, Ordered {

    public static final String X_REQUEST_ID = "X-Request-ID";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Applying Request ID Filter");

        /*String path = exchange.getRequest().getURI().getPath();

        if (path.startsWith("/api/v1/payments/")) {
            log.info("Adding Request ID for path: {}", path);
            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .header(X_REQUEST_ID, UUID.randomUUID().toString())
                    .build();

            ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
            return chain.filter(mutatedExchange);
        }*/

        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                .header(X_REQUEST_ID, UUID.randomUUID().toString())
                .build();

        ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
        return chain.filter(mutatedExchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
