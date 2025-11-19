package com.codebest.product.service;

import com.codebest.product.bean.Product;

public interface ProductService {
    Product getProductByid(Long productId);
}