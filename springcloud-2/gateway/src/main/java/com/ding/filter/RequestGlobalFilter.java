package com.ding.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author tintin
 * @version V1.0
 * @Description 全局过滤器:所有请求都会执行
 * @@copyright
 * @ClassName RequestGlobalFilter
 * @date 2020-12-02 14:39
 */
//@Component
public class RequestGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getURI().toString();
        System.out.println(" uri : " + uri);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
