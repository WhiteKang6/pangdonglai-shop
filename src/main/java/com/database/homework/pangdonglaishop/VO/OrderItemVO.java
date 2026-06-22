package com.database.homework.pangdonglaishop.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "订单明细(含商品名称)")
public class OrderItemVO {
    @Schema(description = "明细ID")
    private Long id;

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "数量")
    private Integer count;

    @Schema(description = "单价")
    private BigDecimal price;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
