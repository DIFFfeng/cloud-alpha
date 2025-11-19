package com.codebest.order.controller;

import com.codebest.order.bean.Order;
import com.codebest.order.service.OrderService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/create")
    public Order createOrder(@PathParam("productId") Long productId,
                             @PathParam("userId") Long userId) {

        Order order = orderService.createOrder(productId,userId);
        return order;
    }
}
