package com.ding;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @Auther: ding
 * @Date: 2019-12-12 20:22
 * @Description: feign 服务
 *  1、feign服务
 *  2、加入config
 *  3、加入消息总线bus
 */
@EnableRetry //开启自动更新
@EnableFeignClients(basePackages = {"com.ding.remote"}) //开启feign
@EnableCircuitBreaker //对hystrixR熔断机制的支持
@EnableHystrix    //开启熔断
//@EnableHystrixDashboard
@EnableDiscoveryClient
@SpringBootApplication
public class FeignApplication {
    public static void main(String[] args){
        SpringApplication.run(FeignApplication.class);
    }


    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
