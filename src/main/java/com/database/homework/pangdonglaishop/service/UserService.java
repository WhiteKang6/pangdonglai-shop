package com.database.homework.pangdonglaishop.service;

import com.database.homework.pangdonglaishop.DTO.*;
import com.database.homework.pangdonglaishop.VO.LoginVO;
import com.database.homework.pangdonglaishop.VO.PageResult;
import com.database.homework.pangdonglaishop.VO.UserVO;
import jakarta.validation.constraints.NotNull;

public interface UserService {
    LoginVO login(UserLoginDTO userLoginDTO);

    void add(UserCreateDTO userCreateDTO);

    void update(UserUpdateDTO userUpdateDTO);

    void updateStatus(Long id, Integer status);

    PageResult<UserVO> page(UserPageQueryDTO userPageQueryDTO);

    UserVO detail(@NotNull Long id);

    void updatePassword(UserPasswordDTO userPasswordDTO);

    void forceLogout(Long id);

    void logout();

    void delete(Long id);

    void resetPassword(Long id);
}
