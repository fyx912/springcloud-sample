package com.ding.fallback;

import com.alibaba.fastjson.JSONObject;
import com.ding.service.HelloRemote;
import com.ding.web.HelloWeb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: ding
 * @Date: 2019-12-15 19:57
 * @Description: feign 方式熔断，服务降级
 */

@Component
public  class HelloServiceHystrix implements HelloRemote {
    private Logger log = LoggerFactory.getLogger(HelloWeb.class);
    @Override
    public String hello() {
        JSONObject json = new JSONObject();
        json.put("code",1);
        json.put("msd","feign进行熔断降级,服务不可用!");
        log.info("熔断:[{}]",json);
        return json.toJSONString();
    }
}
