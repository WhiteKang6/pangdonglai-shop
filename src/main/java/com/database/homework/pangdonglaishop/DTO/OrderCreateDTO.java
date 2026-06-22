package com.database.homework.pangdonglaishop.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "订单创建参数")
public class OrderCreateDTO {

//    @NotNull(message = "操作人不能为空")
//    @Schema(description = "操作人ID")
//    private Long operatorId; //从当前登录用户获取id

    @NotEmpty(message = "订单项不能为空")
    @Valid
    @Schema(description = "订单商品列表")
    private List<OrderItemDTO> items;

    @Data
    @Schema(description = "订单商品项")
    public static class OrderItemDTO {
        @NotNull(message = "商品ID不能为空")
        @Schema(description = "商品ID")
        private Long productId;

        @NotNull(message = "数量不能为空")
        @Schema(description = "数量", example = "2")
        private Integer count;
    }
}