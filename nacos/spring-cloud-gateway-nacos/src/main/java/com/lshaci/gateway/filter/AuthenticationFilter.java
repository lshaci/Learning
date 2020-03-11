package com.lshaci.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthenticationFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.debug("==============AuthenticationFilter==============");
        ServerHttpRequest request = exchange.getRequest();
        System.err.println("uri:" + request.getURI());
        ServerHttpResponse response = exchange.getResponse();
        String authentication = request.getHeaders().getFirst("AUTHENTICATION");
//        if (!"12345".equals(authentication)) {
//            JSONObject message = new JSONObject();
//            message.put("status", false);
//            message.put("message", "登录失效");
//            message.put("code", 40001);
//            byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
//            DataBuffer buffer = response.bufferFactory().wrap(bits);
//            response.setStatusCode(HttpStatus.OK);
//            // 指定编码，否则在浏览器中会中文乱码
//            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
//            return response.writeWith(Mono.just(buffer));
//        }
//        System.err.println(authentication);
        return chain.filter(exchange);
    }
}
