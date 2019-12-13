package com.ding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Auther: ding
 * @Date: 2019-12-12 18:37
 * @Description: eureka注册中心
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {
    public static void main(String[ ] args){
        SpringApplication.run(EurekaApplication.class);
    }

}
