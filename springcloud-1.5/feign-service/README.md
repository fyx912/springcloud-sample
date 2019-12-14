# feign 服务

###### 1、注册到eureka
###### 2、开启feign或ribbon 远程调用
###### 3、开启熔断功能
````
1、引入jar：
    "org.springframework.cloud:spring-cloud-starter-netflix-hystrix:${springcloudNetflixVersion}",
    //Spring Boot Actuator，感应服务端变化
    "org.springframework.boot:spring-boot-starter-actuator:${springBootVersion}"
2、熔断超时配置
ribbon:
  #请求处理的超时时间
  ReadTimeout: 4000
  #请求连接的超时时间
  ConnectTimeout: 5000
  #对当前服务的重试次数
  MaxAutoRetries: 2
  #切换实例的重试次数
  MaxAutoRetriesNextServer: 0
  #对所有操作请求都进行重试
  OkToRetryOnAllOperations: true

feign:
  hystrix:
    enabled: true #开启熔断
  httpclient:
    connection-timeout: 5000
  client:
    config:
      default:
        connect-timeout: 5000
        read-timeout: 5000 #feign方式读取数据超时,开始熔断(读取时间应该大于被调用方法的时间就不会被熔断,否则会被熔断)

#开启hystrix请求超时机制   也可以设置成永久不超时
hystrix:
  command:
    default: #default全局有效，service id指定应用有效
      execution:
        timeout:
          #如果enabled设置为false，则请求超时交给ribbon或feign控制,为true,则超时作为熔断根据
          enabled: false #此值需设置为false，为true的时候下面的熔断超时不起作用立马熔断
      isolation:
        thread:
          timeoutInMilliseconds: 8000 #设置超时时间为20s
````
###### 4、spring cloud config 
###### 5、bus 消息总线
````
1、需引入jar包:
    //spring cloud-bus依赖实现配置自动更新，rabbitmq
    "org.springframework.cloud:spring-cloud-starter-bus-amqp:1.3.5.RELEASE",
    //<!-- 连接配置中心重试的依赖 -->
    'org.springframework.retry:spring-retry:1.2.5.RELEASE'
2、配置:
    spring.cloud.bus.enabled: true # 开启消息跟踪
    #bus-amqp之rabbitMQ配置
    spring:
         rabbitmq:
              host: localhost
              port: 5672
              username: guest
              password: guest
              virtual-host: /
              
    main 函数 需要添加注解:  
        @EnableRetry //开启自动更新
        @RefreshScope
 3、config server 调用post方法:http://localhost:8000/bus/refresh,自动通知客户端刷新消息
````
###### 5、zipkin 链路追踪