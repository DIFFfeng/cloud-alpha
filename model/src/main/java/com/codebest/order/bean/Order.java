package com.codebest.order.bean;

import com.codebest.product.bean.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {
    private Long id;
    private BigDecimal totalAmount;
    private Long userId;
    private String nickName;
    private String address;
    // private List<Object> productList;
    private List<Product> productList; // 公共抽取到 model 就可以
}
