package com.ding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: ding
 * @Date: 2019-12-15 15:00
 * @Description: consul
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulClientApplication {
    public static void main(String[] args){
        SpringApplication.run(ConsulClientApplication.class);
    }
}
