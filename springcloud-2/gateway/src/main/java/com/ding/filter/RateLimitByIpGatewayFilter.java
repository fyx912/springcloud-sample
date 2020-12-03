package com.ding.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName RateLimitByCpuGatewayFilter
 * @date 2020-12-03 16:40
 */
public class RateLimitByIpGatewayFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
