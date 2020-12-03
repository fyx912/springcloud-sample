package com.ding.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tintin
 * @version V1.0
 * @Description 熔断，服务降级
 * @@copyright
 * @ClassName FallbackController
 * @date 2020-11-25 10:36
 */
@RestController
public class FallbackWeb {
    private Logger log = LoggerFactory.getLogger(FallbackWeb.class);

    @GetMapping(value = "/fallback",produces = "application/json;charset=utf-8")
    public Map<String,Object> fallback(){
        Map<String,Object> map = new HashMap<>();
        map.put("code",1000);
        map.put("msg","网络繁忙服务已降级,请稍后再访问.");
        log.info("进行熔断...");
        return map;
    }
}
