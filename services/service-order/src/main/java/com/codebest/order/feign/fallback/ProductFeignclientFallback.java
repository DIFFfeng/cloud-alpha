package com.codebest.order.feign.fallback;
import java.math.BigDecimal;

import com.codebest.order.feign.ProductFeignClient;
import com.codebest.product.bean.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignclientFallback implements ProductFeignClient {

    @Override
    public Product getProductById(Long id) {
        System.out.println("开启 sentinel，商品服务器宕机，执行这个兜底回调 fallback");
        Product product = new Product();
        product.setId(0L);
        product.setPrice(new BigDecimal("1120"));
        product.setProductName("sentinel 和 fallback兜底回调");
        product.setNum(1120);

        return product;
    }
}
