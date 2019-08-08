package com.lshaci.spring_cloud_gateway.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

import java.net.URI;

@Configuration
public class GlobalFilterConfig {

    @Bean
    @Order(99)
    public GlobalFilter authenticationFilter() {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            System.err.println("==========>测试一下过滤器");
            URI uri = request.getURI();
            HttpHeaders headers = request.getHeaders();
            String token = headers.getFirst("token");
//            if (StringUtils.isBlank(token)) {
//                response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                return response.setComplete();
//            }
            System.err.println(token);
            System.err.println(uri.getPath());

//            return chain.filter(exchange);
            return exchange.getSession().flatMap(webSession -> {
                System.err.println("sessionId: " + webSession.getId());
                webSession.getAttributes().put("user", "aaaa");
                return chain.filter(exchange);
            });
        });

    }
}
