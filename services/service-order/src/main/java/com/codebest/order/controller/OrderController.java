package com.codebest.order.controller;

import com.codebest.order.bean.Order;
import com.codebest.order.properties.OrderProperties;
import com.codebest.order.service.OrderService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RefreshScope // 使用 ConfigurationProperties 注解在配置类支持热更新
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    // 常用的配置属性，我也抽取到 properties类中 集中管理，然后用 支持自动刷新
    /* @Value("${order.timeout}")
    String orderTimeout;
    @Value("${order.auto-confirm}")
    String orderAutoConfirm; */

    @Autowired
    OrderProperties orderProperties;

    @GetMapping("/nacosConfig")
    public String config() {
        return "Nacos 配置中心的配置： order.timeout=" + orderProperties.getTimeout() + ";\t order.auto-confirm=" + orderProperties.getAutoConfirm();
    }

    @GetMapping("/create")
    public Order createOrder(@PathParam("productId") Long productId,
                             @PathParam("userId") Long userId) {

        Order order = orderService.createOrder(productId,userId);
        return order;
    }
}
