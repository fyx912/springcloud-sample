package com.ding.web;

import com.alibaba.fastjson.JSONObject;
import com.ding.remote.HelloRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: ding
 * @Date: 2019-12-13 14:47
 * @Description:
 */
@RestController
public class HelloWeb {
    private Logger log = LoggerFactory.getLogger(HelloWeb.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private HelloRemote helloRemote;

    @GetMapping("/")
    public String index( HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","success");

        String services = "Services: "+ discoveryClient.getServices();
        services +=   ",\t port from :"+ request.getServerPort();
        System.out.println(services);

        Map map = new HashMap();
        map.put("serverName","feign-service");
        map.put("service",services);
        jsonObject.put("data",map);
        return jsonObject.toJSONString();
    }

    @GetMapping("hello")
    public String hello(){

        String services = "Services: "+ discoveryClient.getServices();
        System.out.println(services);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","success");
        Map map = new HashMap();
        map.put("serverName","feign-service");
        map.put("service",services);
        map.put("hello",discoveryClient.getLocalServiceInstance());
        jsonObject.put("data",map);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsonObject.toJSONString();
    }

    /**
     * 远程调用
     * @return
     */
    @GetMapping("remote")
    public String remote(){
       String result = helloRemote.hello();
        log.info("result:[{}]",result);
        return result;
    }
}
