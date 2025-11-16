package com.ss.apigateway.filter;

import com.ss.apigateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtValidationGatewayFilterFactory extends
        AbstractGatewayFilterFactory<Object> {

    private final WebClient webClient;
    private final JwtUtil jwtUtil;

    public JwtValidationGatewayFilterFactory(
            WebClient.Builder webClientBuilder,
            @Value("${AUTH_SERVICE_URL}") String authServiceUrl,
            JwtUtil jwtUtil) {
        System.out.println(">>> AUTH_SERVICE_URL resolved to: '" + authServiceUrl + "'");
        this.webClient = webClientBuilder.baseUrl(authServiceUrl).build();
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String token = authHeader.substring(7); // Remove "Bearer " prefix

            return webClient.get()
                    .uri("/validate")
                    .header(HttpHeaders.AUTHORIZATION, authHeader)
                    .retrieve()
                    .toBodilessEntity()
                    .flatMap(response -> {
                        try {
                            // Extract user info from token
                            String userId = jwtUtil.extractUserId(token);
                            String userEmail = jwtUtil.extractEmail(token);
                            String userRole = jwtUtil.extractRole(token);

                            System.out.println(">>> Extracted from token - UserId: " + userId + ", Email: " + userEmail + ", Role: " + userRole);

                            // Create new request with added headers
                            ServerHttpRequest modifiedRequest = exchange.getRequest()
                                    .mutate()
                                    .header("X-User-Id", userId)
                                    .header("X-User-Email", userEmail)
                                    .header("X-User-Role", userRole)
                                    .build();

                            // Create new exchange with modified request
                            ServerWebExchange modifiedExchange = exchange.mutate()
                                    .request(modifiedRequest)
                                    .build();

                            return chain.filter(modifiedExchange);
                        } catch (Exception e) {
                            System.err.println(">>> Error extracting user info from token: " + e.getMessage());
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                            return exchange.getResponse().setComplete();
                        }
                    })
                    .onErrorResume(error -> {
                        System.err.println(">>> Token validation failed: " + error.getMessage());
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    });
        };
    }
}