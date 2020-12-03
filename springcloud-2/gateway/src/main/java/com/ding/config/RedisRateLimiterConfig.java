package com.ding.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author tintin
 * @version V1.0
 * @Description 限流配置
 * @@copyright
 * @ClassName RedisRateLimiterConfig
 * @date 2020-11-25 18:04
 */
@Component
public class RedisRateLimiterConfig {

    /**
     * IP限流
     * 获取请求用户ip作为限流key。
     * @return
     */
    @Bean(value ="ipAddrKeyResolver")
    @Primary
    public KeyResolver ipAddrKeyResolver() {
        System.out.println("限流。。。。。");
        return new KeyResolver(){
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                return Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
            }
        };
    }

    /**
     * 用户限流
     * 获取请求用户id作为限流key。
     * @return
     */
    @Bean(value = "userKeyResolver")
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    }

    /**
     * 接口限流
     * 获取请求地址的uri作为限流key
     * @return
     */
    @Bean(value = "apiKeyResolver")
    KeyResolver apiKeyResolver() {
        System.out.println("限流。。。。。");
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

}
