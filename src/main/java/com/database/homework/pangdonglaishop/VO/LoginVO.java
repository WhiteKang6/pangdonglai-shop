package com.database.homework.pangdonglaishop.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "登录返回")
public class LoginVO {
    @Schema(description = "令牌")
    private String token;

    @Schema(description = "用户信息")
    private UserVO user;
}
