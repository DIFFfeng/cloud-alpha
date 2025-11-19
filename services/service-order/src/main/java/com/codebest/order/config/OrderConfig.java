package com.codebest.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfig {

    @LoadBalanced // 在远程调用上添加 负载均衡注解
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // Ask: 如果注册中心（Nacos）挂了还能请求到服务吗？
    /* Tips：Nacos 会在第一次调用成功注册中心进行 【实例缓存】，记录对应服务 ip+端口
    1、调用过；远程调用不再依赖注册中心，可以通过请求（前提是被缓存的 微服务URL没挂
    2、没调用过：（第一次发起远程调用 Nacos就宕机）；请求不能通过，因为没有缓存请求的服务url */
}
