package com.ding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: ding
 * @Date: 2019-12-15 19:50
 * @Description:
 */
@EnableDiscoveryClient
@SpringBootApplication
public class FeignClientApplication {
    public static void  main (String[] args){
        SpringApplication.run(FeignClientApplication.class);
    }
}
