package com.database.homework.pangdonglaishop.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "用户角色分配参数")
public class UserRoleAssignDTO {
    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "角色ID列表")
    private List<Long> roleIds;
}