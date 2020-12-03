package com.ding.filter;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author tintin
 * @version V1.0
 * @Description 全局权限认证
 * @@copyright
 * @ClassName AuthorizeFilter
 * @date 2020-11-23 18:15
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    private Logger log = LoggerFactory.getLogger(AuthorizeFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        String uri = serverHttpRequest.getURI().getPath();
        log.info("uir:{}",uri);
        if (uri.indexOf("login")>0){
            return  chain.filter(exchange);
        }

//        String token = serverHttpRequest.getHeaders().getFirst("Authorization");
//        if (StringUtils.isEmpty(token)){
//            serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
//            return getVoidMono(serverHttpResponse, 2,"token无效!");
//        }
        return  chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }

    /**
     *  处理类
     * @param serverHttpResponse   http response
     * @param code     状态码
     * @param message   消息
     * @return
     */
    private Mono<Void> getVoidMono(ServerHttpResponse serverHttpResponse, Integer code, String message) {
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("msg",message);

        log.error(jsonObject.toJSONString());

        DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(jsonObject.toJSONString().getBytes());
        return serverHttpResponse.writeWith(Flux.just(dataBuffer));
    }
}
