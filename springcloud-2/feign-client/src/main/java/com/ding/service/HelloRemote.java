package com.ding.service;

import com.ding.fallback.HelloServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: ding
 * @Date: 2019-12-15 19:56
 * @Description:
 */

@Component
@FeignClient(value = "ribbon-client" ,fallback = HelloServiceHystrix.class)
public interface HelloRemote {
    @GetMapping("hello")
    String hello(String name);
}