package com.ding.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: ding
 * @Date: 2019-12-12 20:30
 * @Description:
 */
@RestController()
@RequestMapping(value = "order")
public class OrderWeb {

    @GetMapping("")
    public String getUser(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","success");
        jsonObject.put("data","order");
        return jsonObject.toJSONString();
    }
}
