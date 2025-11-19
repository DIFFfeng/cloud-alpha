package com.codebest.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// 配置项多可以用下面注解 支持批量绑定， 每个前面自动加上 order，然后 配置中心的 auto-confirm 换为 驼峰命名 autoConfirm
@Component
@ConfigurationProperties(prefix =  "order")
@Data
public class OrderProperties {
    // String orderTimeout;
    String Timeout;
    String AutoConfirm;

    String dbUrl; // 测试 nacos.config.namespace 的配置（数据隔离）
}
