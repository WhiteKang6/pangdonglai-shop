package com.database.homework.pangdonglaishop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private Integer status;//0-下架 1-上架
    private String imageUrl;
    private Long categoryId;
    private LocalDateTime createTime;
}
