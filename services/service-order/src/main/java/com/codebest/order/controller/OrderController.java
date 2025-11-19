package com.codebest.order.controller;

import com.codebest.order.bean.Order;
import com.codebest.order.service.OrderService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope // 这个注解可以不重启情况下，自动刷新 配置中心实时更新的内容
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    // 配置好 Nacos配置中心， 就可以用它web端发布的配置 （dataID=service-order.properties ）
    @Value("${order.timeout}")
    String orderTimeout;
    @Value("${order.auto-confirm}")
    String orderAutoConfirm;

    @GetMapping("/nacosConfig")
    public String config() {
        return "Naocos 配置中心的配置： order.timeout=" + orderTimeout + ";\t order.auto-confirm=" + orderAutoConfirm;
    }

    @GetMapping("/create")
    public Order createOrder(@PathParam("productId") Long productId,
                             @PathParam("userId") Long userId) {

        Order order = orderService.createOrder(productId,userId);
        return order;
    }
}
