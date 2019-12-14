package com.ding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

/**
 * @Auther: ding
 * @Date: 2019-12-14 17:14
 * @Description: 服务链路追踪
 */
@EnableZipkinServer
@EnableDiscoveryClient
@SpringBootApplication
public class SleuthZipkinApplication {
    public static void main(String[] args){
        SpringApplication.run(SleuthZipkinApplication.class,args);
    }
}
