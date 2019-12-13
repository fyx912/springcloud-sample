package com.ding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * @Auther: ding
 * @Date: 2019-12-13 13:48
 * @Description: zuul网关
 */
@EnableHystrix     //熔断
@EnableZuulProxy   //路由代理
@EnableZuulServer //开启路由
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulApplication {

    public static void main(String[] args){
        SpringApplication.run(ZuulApplication.class);
    }
}
