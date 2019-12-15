package com.ding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * @Auther: ding
 * @Date: 2019-12-15 19:30
 * @Description:
 */
@EnableZuulServer
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulApiApplication {
    public  static  void  main(String[] args){
        SpringApplication.run(ZuulApiApplication.class);
    }
}
