package com.database.homework.pangdonglaishop.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "分类更新参数")
public class CategoryUpdateDTO {
    @NotNull(message = "分类ID不能为空")
    @Schema(description = "分类ID")
    private Long id;

    @NotBlank(message = "分类名称不能为空")
    @Schema(description = "分类名称", example = "饮料")
    private String name;
}