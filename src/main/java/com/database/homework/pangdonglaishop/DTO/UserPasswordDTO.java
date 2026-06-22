package com.database.homework.pangdonglaishop.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "用户修改密码参数")
public class UserPasswordDTO {
    @NotBlank(message = "旧密码不能为空")
    @Schema(description = "旧密码")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    @Schema(description = "新密码")
    private String newPassword;


    @NotBlank(message = "确认密码不能为空")
    @Schema(description = "确认密码")
    private String confirmPassword;
}