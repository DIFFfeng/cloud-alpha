package com.codebest.order.service;

import com.codebest.order.bean.Order;

public interface OrderService {
    Order createOrder(Long productId, Long userId);
}
