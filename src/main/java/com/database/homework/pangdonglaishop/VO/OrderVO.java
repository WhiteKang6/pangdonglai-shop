package com.database.homework.pangdonglaishop.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "订单信息(含明细和操作人)")
public class OrderVO {
    @Schema(description = "订单ID")
    private Long id;

    @Schema(description = "订单号")
    private String orderSn;

    @Schema(description = "总金额")
    private BigDecimal totalAmount;

    @Schema(description = "操作人ID")
    private Long operatorId;

    @Schema(description = "操作人姓名")
    private String operatorName;

    @Schema(description = "订单明细")
    private List<OrderItemVO> items;//分页查询时,不查询订单商品

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
