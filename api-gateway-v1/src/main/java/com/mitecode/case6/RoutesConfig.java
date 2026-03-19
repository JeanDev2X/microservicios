package com.mitecode.case6;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@Profile("case6")
@AllArgsConstructor
public class RoutesConfig {

    private final DemoModeGatewayFilter demoModeGatewayFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

        log.info("Configuring Gateway Routes for Case 6");

        //CASE1
        return builder.routes()
                .route("api-payment-service-v1", r -> r
                        .path("/api/v1/payments/**")
                        .and().method("GET", "POST")
                        .and().header("X-Client-Version", "v1")
                        //.uri("http://localhost:50050") // Local
                        .filters(f -> f.filter(demoModeGatewayFilter))
                        .uri("lb://API-PAYMENT-SERVICE-V1") // Eureka
                )
                .build();

        // CASE 2

//        return builder.routes()
//                // Ruta para Payment V2 (más específica con header)
//                .route("api-payment-service-v2", r -> r
//                        .path("/mitocode/pagos/**")
//                        .and().method("GET", "POST")
//                        .and().header("X-Client-Version", "v2")
//                        .filters(f -> f.rewritePath(
//                                "/mitocode/pagos/(?<segment>.*)",
//                                "/api/v2/payments/${segment}"
//                        ))
//                        .uri("lb://API-PAYMENT-SERVICE-V2") // Resuelto por Eureka
//                )
//
//                // Ruta para Payment V1
//                .route("api-payment-service-v1", r -> r
//                        .path("/mitocode/pagos/**")
//                        .and().method("GET", "POST")
//                        .and().header("X-Client-Version", "v1")
//                        .filters(f -> f.rewritePath(
//                                "/mitocode/pagos/(?<segment>.*)",
//                                "/api/v1/payments/${segment}"
//                        ))
//                        .uri("lb://API-PAYMENT-SERVICE-V1") // Resuelto por Eureka
//                )
//
//                .build();

        // CASE 4

//        return builder.routes()
//                .route("api-payment-service-v1", r -> r
//                        .path("/mitocode/services/**") // predicado Path
//                        .and()
//                        .method("GET", "POST")         // predicado Method
//                        .and()
//                        .header("X-Client-Version", "v1") // predicado Header
//                        .filters(f -> f
//                                .stripPrefix(2) // elimina /mitocode/services
//                                .filter(new CacheRequestBodyGatewayFilterFactory().apply(config -> {})) // CacheRequestBody
//                                .retry(config -> config
//                                        .setRetries(3) // 3 reintentos
//                                        .setStatuses(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
//                                        .setMethods(org.springframework.http.HttpMethod.POST)
//                                        .setBackoff(
//                                                new RetryGatewayFilterFactory.BackoffConfig
//                                                        (
//                                                                java.time.Duration.ofMillis(1000), // firstBackoff
//                                                                java.time.Duration.ofSeconds(10),  // maxBackoff
//                                                                2,                                // factor
//                                                                false                             // basedOnPreviousValue
//                                                        )
//                                        )
//                                )
//                        )
//                        .uri("lb://API-PAYMENT-SERVICE-V1") // serviceId en Eureka / LoadBalancer
//                )
//                .build();

    }
}
