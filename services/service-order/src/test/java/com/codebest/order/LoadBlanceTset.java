package com.codebest.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class LoadBlanceTset {

    @Autowired
    LoadBalancerClient loadBalancer;

    @Test
    void test() {
        // 选择某个微服务的 地址
        ServiceInstance choose = loadBalancer.choose("service-product");
        System.out.println(choose.getHost() + ":" + choose.getPort());

        // 这里执行多次 会看到输出 轮询调用不同的微服务地址 9000~9002
        choose = loadBalancer.choose("service-product");
        System.out.println(choose.getHost() + ":" + choose.getPort());

        choose = loadBalancer.choose("service-product");
        System.out.println(choose.getHost() + ":" + choose.getPort());

        choose = loadBalancer.choose("service-product");
        System.out.println(choose.getHost() + ":" + choose.getPort());
    }

}
