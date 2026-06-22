package com.database.homework.pangdonglaishop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    private Long id;
    private Long userId;
    private String token;
    private LocalDateTime loginTime;
    private LocalDateTime expireTime;
    private LocalDateTime lastActiveTime;
    private Integer status;
}
