package com.ding.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: ding
 * @Date: 2019-12-15 15:30
 * @Description:
 */
@RestController
public class HelloWeb {

    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("/")
    public String index(){
        return  "hello consul client";
    }

    @GetMapping("home")
    public String home(){
       return  new JSONObject().put("service",discoveryClient.getServices()).toString();
    }
}
