package com.database.homework.pangdonglaishop.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "用户创建参数")
public class UserCreateDTO {
    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名", example = "zhangsan")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码", example = "123456")
    private String password;

    @NotBlank(message = "姓名不能为空")
    @Schema(description = "姓名", example = "张三")
    private String name;

    @Pattern(regexp = "^\\d+$", message = "年龄必须是整数")
    @Schema(description = "年龄", example = "18")
    private int age;

    @Schema(description = "性别", example = "0")
    @Pattern(regexp = "^0|1$", message = "性别必须是0或1")
    private int sex; //0:男 1:女
}