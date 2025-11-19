package com.codebest.product.controller;

import com.codebest.product.bean.Product;
import com.codebest.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    // 查询商品
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id")  Long productId) {

        Product product  = productService.getProductByid(productId);
        return product;
    }
}
