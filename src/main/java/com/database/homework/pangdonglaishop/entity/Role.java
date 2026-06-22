package com.database.homework.pangdonglaishop.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Role {
    private Long id;
    private String code;
    private String name;
    private LocalDateTime createTime;
}
