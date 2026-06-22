package com.database.homework.pangdonglaishop.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "订单分页查询参数")
public class OrderPageQueryDTO {
    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;
    @Schema(description = "每页数量", example = "10")
    private Integer pageSize = 10;

    //查询条件
    @Schema(description = "订单号")
    private String orderSn; //模糊查询
    @Schema(description = "操作人姓名")
    private String operatorName; //模糊查询
    @Schema(description = "起始时间")
    private LocalDateTime beginTime;
    @Schema(description = "结束时间")
    private LocalDateTime endTime;

}
