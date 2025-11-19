package com.codebest.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProductServiceConfig {

    // 使用 bean 注解放到容器中， 以后每次远程调用就直接用小写这个 restTemplaye 不需要再new
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
