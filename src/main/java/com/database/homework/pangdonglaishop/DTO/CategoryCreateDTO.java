package com.database.homework.pangdonglaishop.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "分类创建参数")
public class CategoryCreateDTO {
    @NotBlank(message = "分类名称不能为空")
    @Schema(description = "分类名称", example = "食品")
    private String name;
}