package com.database.homework.pangdonglaishop.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "用户信息更新参数")
public class UserUpdateDTO {
    @NotNull(message = "用户id不能为空")
    @Schema(description = "用户ID")
    private Long id;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "年龄", example = "18")
    @Pattern(regexp = "^\\d+$", message = "年龄必须是整数")
    private int age;

    @Schema(description = "性别")
    @Pattern(regexp = "^0|1$", message = "性别必须是0或1")
    private int sex; //0:男 1:女

}