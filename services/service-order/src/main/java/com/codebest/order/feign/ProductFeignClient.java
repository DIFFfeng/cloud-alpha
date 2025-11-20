package com.codebest.order.feign;

import com.codebest.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product") // feign 客户端（可以加 url 就给固定给这个地址请求）
public interface ProductFeignClient {

    // mvc注解的两套使用逻辑
    // 1、标注在Controller.上，是接受这样的请求
    // 2、标注在Feignclient上，是发送这样的请求 出去给@FeignClient(value = "service-product") 这个 feign 客户端
    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long id);

    // 业务API（自己写的直接复制对应 Controller的方法签名）  查询商品
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id")  Long productId);
}
