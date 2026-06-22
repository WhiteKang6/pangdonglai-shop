package com.database.homework.pangdonglaishop.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "角色权限分配参数")
public class RolePermissionAssignDTO {
    @NotNull(message = "角色ID不能为空")
    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "权限ID列表")
    private List<Long> permissionIds;
}