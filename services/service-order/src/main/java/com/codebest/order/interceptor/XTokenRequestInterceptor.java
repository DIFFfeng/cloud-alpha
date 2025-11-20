package com.codebest.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

// 请求拦截器
@Component
public class XTokenRequestInterceptor implements RequestInterceptor {

    // 实现 RequestInterceptor 接口，重写 apply方法， 如果需要修改这次请求， 就改 参数 template即可
    @Override
    public void apply(RequestTemplate template) {
        System.out.println("XTokenRequestInterceptor拦截器启动");
        template.header("x-Token", UUID.randomUUID().toString());
    }
}
