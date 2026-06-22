package com.database.homework.pangdonglaishop.controller;

import com.database.homework.pangdonglaishop.DTO.*;
import com.database.homework.pangdonglaishop.VO.LoginVO;
import com.database.homework.pangdonglaishop.VO.PageResult;
import com.database.homework.pangdonglaishop.VO.Result;
import com.database.homework.pangdonglaishop.VO.UserVO;
import com.database.homework.pangdonglaishop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "用户模块")
@Slf4j
public class UserContoller {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return
     */
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录参数: {}", userLoginDTO);
        LoginVO loginVO = userService.login(userLoginDTO);
        return Result.success(loginVO);
    }


    /**
     * 添加用户
     *
     * @param userCreateDTO
     * @return
     */
    @Operation(summary = "添加用户")
    @PostMapping("/add")
    public Result add(@RequestBody UserCreateDTO userCreateDTO) {
        log.info("用户创建参数: {}", userCreateDTO);
        userService.add(userCreateDTO);
        return Result.success();
    }

    /**
     * 修改用户
     *
     * @param userUpdateDTO
     * @return
     */
    @PutMapping("/update")
    @Operation(summary = "修改用户")
    public Result update(@RequestBody UserUpdateDTO userUpdateDTO) {
        log.info("用户修改参数: {}", userUpdateDTO);
        userService.update(userUpdateDTO);
        return Result.success();
    }

    /**
     * 修改用户状态
     *
     * @param id
     * @param status
     * @return
     */
    @Operation(summary = "修改用户状态")
    @PutMapping("/status")
    public Result updateStatus(@RequestParam @NotNull Long id, @RequestParam @NotNull Integer status) {
        log.info("修改用户状态:{},{}", id, status);
        userService.updateStatus(id, status);
        return Result.success();
    }


    /**
     * 分页查询用户列表
     * @param userPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询用户列表")
    public Result<PageResult<UserVO>> page(UserPageQueryDTO userPageQueryDTO) {
        log.info("用户分页查询参数: {}", userPageQueryDTO);
        PageResult<UserVO> pageResult = userService.page(userPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 查询用户详情
     *
     * @param id
     * @return
     */
    @Operation(summary = "查询用户详情")
    @GetMapping("/detail/{id}")
    public Result<UserVO> detail(@PathVariable @NotNull Long id){
        log.info("查询用户详情参数: {}", id);
        UserVO userVO = userService.detail(id);
        return Result.success(userVO);
    }

    /**
     * 修改用户密码参数
     * @param userPasswordDTO
     * @return
     */
    @PostMapping("/updatePassword")
    @Operation(summary = "修改用户密码")
    public Result updatePassword(@RequestBody UserPasswordDTO userPasswordDTO) {
        log.info("修改用户密码参数: {}", userPasswordDTO);
        userService.updatePassword(userPasswordDTO);
        return Result.success();
    }


    /**
     * 强制登出
     * @param id
     * @return
     */
    @PostMapping("/forceLogout")
    @Operation(summary = "强制登出", description = "强制登出接口")
    public Result forceLogout(@RequestParam Long id){
        log.info("强制登出参数：{}", id);
        userService.forceLogout(id);
        return Result.success();
    }

    /**
     * 登出
     * @return
     */
    @PostMapping("/logout")
    @Operation(summary = "登出", description = "登出接口")
    public Result logout(){
        log.info("登出");
        userService.logout();
        return Result.success();
    }

    /**
     * 删除员工
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除用户", description = "删除用户接口")
    public Result delete(@PathVariable Long id){
        log.info("删除用户参数：{}", id);
        userService.delete(id);
        return Result.success();
    }


    /**
     * 重置密码
     * @param id
     * @return
     */
    @PostMapping("/resetPassword/{id}")
    @Operation(summary = "重置密码", description = "重置密码接口")
    public Result resetPassword(@PathVariable Long id){
        log.info("重置密码参数：{}", id);
        userService.resetPassword(id);
        return Result.success();
    }

}
