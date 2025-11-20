package com.codebest.order.service.impl;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.codebest.order.bean.Order;
import com.codebest.order.service.OrderService;
import com.codebest.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OrderSerivceImpl implements OrderService {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient; // 使用 SpringCloud提供的负载均衡

    public Order createOrder(Long productId, Long userId) {
        // Product product = getProductFromRemote(productId);
        Product product = getProductFromRemoteWithLoadBalancerAnnotation(productId); // 使用注解-负载均衡的远程调用版本
        Order order = new Order();

        order.setId(0L);
        // 总金额 = 远程调用得到的 商品价格 * 商品数量
        BigDecimal rSumAmount = product.getPrice().multiply(new BigDecimal(product.getNum()));
        order.setTotalAmount(rSumAmount);
        order.setUserId(0L);
        order.setNickName("weiFuWu - Java");
        order.setAddress("SpringCloud 微服务");
        // 远程查询商品列表
        order.setProductList(Arrays.asList(product));

        return order;
    }

    // 默认版本 1： 远程调用微服务 获得商品 （无负载均衡
    public Product getProductFromRemote(Long productId) {
        // 获取到 “商品服务" 所在机器IP+port  这个 discoveryClient.getInstances 是发现所有地址的
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");

        ServiceInstance instance = instances.get(0);
        // 拼接出远程的 URL地址， 通过它调用远程数据
        // http://localhost:9000/product/2025
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        log.info("远程请求是：{}", url);

        // 给远程发送请求 cloud提供 RestTemplate组件，很方便的提供请求 （是线程安全的，全局可只有一个，所以生成一个 配置类来注入使用）
        Product rProduct = restTemplate.getForObject(url, Product.class);// 第二个参数就是 请求到的json自动转为这个 java Bean
        return rProduct;
    }

    // 升级版本 2 ：完成负载均衡 发送请求
    public Product getProductFromRemoteWithLoadBalancer(Long productId) {
        // List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        // 需要导入 spring-cloud-loadbalancer 依赖并注入即可用
        ServiceInstance choose = loadBalancerClient.choose("service-product");

        // http://localhost:9000~9002/product/2025 中间的服务支持负载均衡了（默认轮询
        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/product/" + productId;
        log.info("负载均衡的远程请求是：{}", url);

        // 给远程发送请求 cloud提供 RestTemplate组件，很方便的提供请求 （是线程安全的，全局可只有一个，所以生成一个 配置类来注入使用）
        Product rProduct = restTemplate.getForObject(url, Product.class);// 第二个参数就是 请求到的json自动转为这个 java Bean
        return rProduct;
    }

    // 升级版本 3 ：基于 loadBalancer 注解自动在远程调用完成负载均衡 发送请求
    public Product getProductFromRemoteWithLoadBalancerAnnotation(Long productId) {

        // http://localhost:9000~9002/product/2025 注解支持 restTemplate 自动开启负载均衡了，中间 ip+端口 换为”服务名“就好
        String url = "http://service-product/product/" + productId;
        log.info("注解支持-负载均衡的远程请求是：{}", url);

        // 给远程发送请求 cloud提供 RestTemplate组件，很方便的提供请求 （是线程安全的，全局可只有一个，所以生成一个 配置类来注入使用）
        Product rProduct = restTemplate.getForObject(url, Product.class);// 第二个参数就是 请求到的json自动转为这个 java Bean
        return rProduct;
    }


}
