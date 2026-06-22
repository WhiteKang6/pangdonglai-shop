package com.database.homework.pangdonglaishop.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

@Data
@Schema(description = "商品创建参数")
public class ProductCreateDTO {
    @NotBlank(message = "商品名称不能为空")
    @Schema(description = "商品名称", example = "可口可乐")
    private String name;

    @PositiveOrZero(message = "价格不能为负数")
    @Schema(description = "价格", example = "3.50")
    private BigDecimal price;

    @PositiveOrZero(message = "库存不能为负数")
    @Schema(description = "库存", example = "100")
    private Integer stock;

    @Schema(description = "图片URL")
    @URL
    private String imageUrl;
    @Schema(description = "分类ID")
    private Long categoryId;
}