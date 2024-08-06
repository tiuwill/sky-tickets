package br.com.springcloud.skytikects.gateway.sky_tickets_gateway_server.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("catalog-service", r ->
                    r.path("/catalog/**")
                    .uri("http://localhost:8080"))
                .route("greetings", r->
                    r.path("/greetings/**")
                    .uri("http://localhost:8080"))
                .route("order-service", r->
                        r.path("/order/**")
                        .uri("http://localhost:8081"))
                .build();
    }

}
