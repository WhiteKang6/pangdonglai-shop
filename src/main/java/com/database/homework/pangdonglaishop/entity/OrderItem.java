package com.database.homework.pangdonglaishop.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderItem {
    private Long id;
    private Long orderId; //关联订单的id
    private Long productId; //关联商品的id
    private Integer count; //商品数量
    private BigDecimal price; //商品单价
    private LocalDateTime createTime; //创建时间
}
