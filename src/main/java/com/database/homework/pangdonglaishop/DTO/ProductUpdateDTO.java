package com.database.homework.pangdonglaishop.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

@Data
@Schema(description = "商品更新参数")
public class ProductUpdateDTO {
    @NotNull(message = "商品ID不能为空")
    @Schema(description = "商品ID")
    private Long id;

    @Schema(description = "商品名称")
    private String name;

    @PositiveOrZero(message = "价格不能为负数")
    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "图片URL")
    @URL
    private String imageUrl;
    @PositiveOrZero(message = "库存不能为负数")
    @Schema(description = "库存")
    private Integer stock;

//    @Schema(description = "状态(0下架 1上架)", allowableValues = {"0", "1"})
//    private Integer status;

    @Schema(description = "分类ID")
    private Long categoryId;
}