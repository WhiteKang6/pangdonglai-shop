package com.database.homework.pangdonglaishop.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "权限创建参数")
public class PermissionCreateDTO {
    @NotBlank(message = "权限编码不能为空")
    @Schema(description = "权限编码", example = "ORDER_CREATE")
    private String code;

    @NotBlank(message = "权限名称不能为空")
    @Schema(description = "权限名称", example = "创建订单")
    private String name;
}