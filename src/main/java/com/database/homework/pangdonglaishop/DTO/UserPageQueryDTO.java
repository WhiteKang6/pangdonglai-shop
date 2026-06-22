package com.database.homework.pangdonglaishop.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "用户分页查询参数")
public class UserPageQueryDTO {
    @Schema(description = "关键词(按用户名/姓名模糊搜索)")
    private String keyword;

    @Schema(description = "状态(0封禁 1正常)", allowableValues = {"0", "1"})
    private Integer status;

    @Schema(description = "年龄")

    private Integer age;

    @Schema(description = "性别(0男 1女)", allowableValues = {"0", "1"})
    private Integer sex;


    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页条数", example = "10")
    private Integer pageSize = 10;
}