package com.ding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @Auther: ding
 * @Date: 2019-12-13 17:11
 * @Description: 监控,聚合整个集群下的监控状况
 */
/** 开启 Hystrix Dashboard 监控功能 */
@EnableHystrixDashboard
/** 开启 Hystrix 集群监控 */
@EnableTurbine
@EnableDiscoveryClient
@SpringBootApplication
public class HystrixTurbineDashboardApplication {
    public static void  main(String[] args) {
        SpringApplication.run(HystrixTurbineDashboardApplication.class);
    }
}
