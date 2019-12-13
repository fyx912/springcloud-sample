package com.ding.config;

import com.netflix.hystrix.HystrixCommand;
import feign.Feign;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @Auther: ding
 * @Date: 2019-12-13 18:42
 * @Description: feign+hystrix根据serviceId配置hystrix.command
 */
@Configuration
//@ConditionalOnClass({HystrixCommand.class, HystrixFeign.class})
public class HystrixFeignConfiguration {
//
//    @Autowired
//    private ConfigurableEnvironment environment;
//
//
//    @Bean
//    @Scope("prototype")
//    @ConditionalOnProperty(name = "feign.hystrix.enabled", matchIfMissing = true)
//    public Feign.Builder feignHystrixBuilder() {
//
//        return HystrixFeign.builder().setterFactory(getSetterFactory());
//    }
//
//    private SetterFactory getSetterFactory(){
//        return new FmSetterFactory(environment);
//    }

    //禁用当前配置的hystrix，局部禁用
//    @Bean
//    @Scope("prototype")
//    public Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }
}
