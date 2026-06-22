package com.database.homework.pangdonglaishop.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
@Schema(description = "商品分页查询参数")
public class ProductPageQueryDTO {
    @Schema(description = "分类ID", example = "1")
    private Long categoryId;

    @Schema(description = "商品状态(0下架 1上架)", allowableValues = {"0", "1"})
    private Integer status;

    @Schema(description = "关键词(按名称模糊搜索)", example = "可乐")
    private String keyword;

    @Schema(description = "图片URL")
    @URL
    private String imageUrl;
    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页条数", example = "10")
    private Integer pageSize = 10;
}