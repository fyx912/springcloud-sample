package com.ding.web;

import com.alibaba.fastjson.JSONObject;
import com.ding.service.RibbonHelloService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: ding
 * @Date: 2019-12-15 19:21
 * @Description:
 */
@RestController
public class HelloWeb {
    private Logger log = LoggerFactory.getLogger(HelloWeb.class);

    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    RibbonHelloService ribbonHelloService;

    @GetMapping("/")
    public String index( HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","success");

        String services = "Services: "+ discoveryClient.getServices();
        services +=   ",port from :"+ request.getServerPort();
        System.out.println(services);

        Map map = new HashMap();
        map.put("serverName","feign-service");
        map.put("service",services);

        map.put("description"," I from ribbon!");
        jsonObject.put("data",map);
        return jsonObject.toJSONString();
    }

    @GetMapping("hello")
    public String hello(HttpServletRequest request, String name){
        log.info("request address:[{}],url:[{}]",request.getRemoteAddr()+request.getServerPort(),request.getRequestURL());
        String services = "Services: "+ discoveryClient.getServices();
        System.out.println(services);
        System.out.println("name:"+name);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","success");
        Map map = new HashMap();
        map.put("serverName","feign-service");
        map.put("service",services);
        map.put("hello",discoveryClient.getServices());
        jsonObject.put("data",map);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsonObject.toJSONString();
    }

    @GetMapping("remote")
    public String remote(String name){
        if (StringUtils.isEmpty(name)){
            name = " I from ribbon";
        }
        String result = ribbonHelloService.helloService(name);
        log.info("result:[{}]",result);
        return result;
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("feign")
    public String remote2(String name){
        if (StringUtils.isEmpty(name)){
            name = " I from ribbon";
        }
        String url = "http://feign-client/";
        url += "?name="+name;
        String result = restTemplate.getForObject(url,String.class);
        log.info("result:[{}]",result);
        return result;
    }
}