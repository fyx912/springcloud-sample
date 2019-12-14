package com.ding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Auther: ding
 * @Date: 2019-12-14 13:04
 * @Description: 配置中心
 */
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigApplication {

    public static void main(String[] args){
        SpringApplication.run(ConfigApplication.class);
    }
}
