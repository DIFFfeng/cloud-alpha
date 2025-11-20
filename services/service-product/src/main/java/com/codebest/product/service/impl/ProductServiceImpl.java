package com.codebest.product.service.impl;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import com.codebest.product.bean.Product;
import com.codebest.product.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProductByid(Long productId) {
        Product product = new Product();
        // 使用 generatorAllSetter 插件直接在 new对象后 -> 生成 defalut Value 的值
        product.setId(0L);
        product.setPrice(new BigDecimal("1119"));
        product.setProductName("苹果 - " + productId);
        product.setNum(2);

        // 写个延时测试 openFeign的超时
        try {
            TimeUnit.SECONDS.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        return product;
    }
}
