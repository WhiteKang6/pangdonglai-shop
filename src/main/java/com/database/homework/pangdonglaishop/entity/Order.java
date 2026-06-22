package com.database.homework.pangdonglaishop.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order {
    private Long id;
    private String orderSn; //订单号
    private BigDecimal totalAmount;
    private Long operatorId; //关联用户的id
    private LocalDateTime createTime;
}
