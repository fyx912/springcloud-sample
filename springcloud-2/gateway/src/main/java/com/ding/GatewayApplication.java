package com.ding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @Auther: ding
 * @Date: 2019-12-15 20:51
 * @Description:
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args){
        SpringApplication.run(GatewayApplication.class);
    }

//    @Bean
//    public RouteLocator myRoute(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route(predicateSpec -> predicateSpec
//                        .path("/hello")
//                        .filters(gf -> gf.addRequestHeader("hello","world"))
//                        .uri("http://feign-client:9020"))
//                .build();
//    }
}
