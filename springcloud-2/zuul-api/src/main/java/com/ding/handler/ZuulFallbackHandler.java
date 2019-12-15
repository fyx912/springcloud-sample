package com.ding.handler;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Auther: ding
 * @Date: 2019-12-13 16:32
 * @Description: 网关熔断
 */
@Component
public class ZuulFallbackHandler implements FallbackProvider {
    private static Logger log = LoggerFactory.getLogger(ZuulFallbackHandler.class);

    /**
     * getRoute方法的返回值就是要监听的挂掉的微服务名字
     * api服务id，如果需要所有调用都支持回退，则return "*"或return null
     * @return
     */
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        log.info("--> route:{}进行熔断降级", this.getRoute());

        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                JSONObject json = new JSONObject();
                json.put("code",1);
                json.put("msg","服务不可用");
                json.put("describe","进行熔断,服务降级!");
                return new ByteArrayInputStream(json.toJSONString().getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", StandardCharsets.UTF_8);
                headers.setContentType(mt);
                return headers;
            }
        };
    }
}
