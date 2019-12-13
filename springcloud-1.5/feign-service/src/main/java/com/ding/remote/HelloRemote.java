package com.ding.remote;

import com.ding.fallback.HelloServiceHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: ding
 * @Date: 2019-12-13 18:04
 * @Description: feign远程调用
 */
@Component
@FeignClient(value = "ribbon-service" ,fallback = HelloServiceHystrix.class)
public interface HelloRemote {
    @GetMapping("hello")
    String hello();
}
