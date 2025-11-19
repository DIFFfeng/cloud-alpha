package com.codebest.order.controller;

import com.codebest.order.bean.Order;
import com.codebest.order.properties.OrderProperties;
import com.codebest.order.service.OrderService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RefreshScope // 使用 ConfigurationProperties 注解在配置类支持热更新
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderProperties orderProperties;

    @GetMapping("/nacosConfig")
    public String config() {
        return "Nacos 配置中心的配置： order.timeout=" + orderProperties.getTimeout() +
                "; order.auto-confirm=" + orderProperties.getAutoConfirm() +
                "; order.db-url=" + orderProperties.getDbUrl();
    }

    @GetMapping("/create")
    public Order createOrder(@PathParam("productId") Long productId,
                             @PathParam("userId") Long userId) {

        Order order = orderService.createOrder(productId,userId);
        return order;
    }
}
