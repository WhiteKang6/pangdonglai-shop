package com.database.homework.pangdonglaishop.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "商品信息(含分类名称)")
public class ProductVO {
    @Schema(description = "商品ID")
    private Long id;

    @Schema(description = "商品名称")
    private String name;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "库存")
    private Integer stock;

    @Schema(description = "状态(0下架 1上架)")
    private Integer status;

    @Schema(description = "分类ID")
    private Long categoryId;
    @Schema(description = "图片URL")
    @URL
    private String imageUrl;
    @Schema(description = "分类名称")
    private String categoryName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
