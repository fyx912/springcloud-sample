package com.ding.filter;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther: ding
 * @Date: 2019-12-13 15:44
 * @Description: 限流
 */
@Component
public class MyLimiter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(MyLimiter.class);
    //谷歌令牌桶的实现 100/s 令牌
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2);

    /**
     * 过滤器类型
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        if (!RATE_LIMITER.tryAcquire()){
            log.info("开始限流");
            try {
                HttpServletResponse response =  context.getResponse();
                response.reset();
                response.setContentType("application/json;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                JSONObject json = new JSONObject();
                json.put("code",1);
                json.put("msg","开始限流");

                PrintWriter pw = response.getWriter();
                pw.print(json.toJSONString());
                pw.flush();
                pw.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            context.removeRouteHost();
            return null;
        };
        log.info("ok");
        return null;
    }
}