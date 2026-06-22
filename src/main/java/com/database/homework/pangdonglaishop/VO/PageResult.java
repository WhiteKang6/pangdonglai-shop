package com.database.homework.pangdonglaishop.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Schema(description = "分页结果")
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    @Schema(description = "总记录数")
    private Long total;

    @Schema(description = "数据列表")
    private List<T> records;


}
