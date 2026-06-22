package com.database.homework.pangdonglaishop.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private int age;
    private int sex; //0:男 1:女
    private Integer status;
    private LocalDateTime createTime;
}
