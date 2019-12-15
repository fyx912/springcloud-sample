package com.ding.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: ding
 * @Date: 2019-12-15 19:59
 * @Description:
 */

@RestController
@RequestMapping(value = "user")
public class UserWeb {

    @GetMapping("")
    public String getUser(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","success");
        Map map = new HashMap();
        map.put("name","tinTin");
        map.put("service","feign");
        jsonObject.put("data",map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable int id){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","success");
        Map map = new HashMap();
        map.put("id",id);
        map.put("name","fyx912");
        map.put("service","feign");
        jsonObject.put("data",map);
        return jsonObject.toJSONString();
    }
}
