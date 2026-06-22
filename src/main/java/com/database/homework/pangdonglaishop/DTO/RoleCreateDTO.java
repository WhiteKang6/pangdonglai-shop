package com.database.homework.pangdonglaishop.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "角色创建参数")
public class RoleCreateDTO {
    @NotBlank(message = "角色编码不能为空")
    @Schema(description = "角色编码", example = "CASHIER")
    private String code;

    @NotBlank(message = "角色名称不能为空")
    @Schema(description = "角色名称", example = "收银员")
    private String name;
}