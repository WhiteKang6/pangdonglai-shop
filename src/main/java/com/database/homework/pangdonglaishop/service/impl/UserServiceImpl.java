package com.database.homework.pangdonglaishop.service.impl;

import com.database.homework.pangdonglaishop.DTO.*;
import com.database.homework.pangdonglaishop.VO.LoginVO;

import com.database.homework.pangdonglaishop.VO.PageResult;
import com.database.homework.pangdonglaishop.VO.UserVO;
import com.database.homework.pangdonglaishop.entity.User;
import com.database.homework.pangdonglaishop.entity.UserLogin;
import com.database.homework.pangdonglaishop.exception.BaseException;
import com.database.homework.pangdonglaishop.mapper.OrderMapper;
import com.database.homework.pangdonglaishop.mapper.UserLoginMapper;
import com.database.homework.pangdonglaishop.mapper.UserMapper;
import com.database.homework.pangdonglaishop.service.UserService;
import com.database.homework.pangdonglaishop.util.PasswordUtil;
import com.database.homework.pangdonglaishop.util.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public LoginVO login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        //根据用户名查询密码
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new BaseException("用户名不存在");
        }
        //判断密码是否匹配
        if (!PasswordUtil.matches(password, user.getPassword())) {
            throw new BaseException("密码错误");
        }
        //判断用户账号的状态是否为正常
        if (user.getStatus() != 1) {
            throw new BaseException("用户账号状态异常");
        }
        //登录成功
        //把id保存到threadlocal
        /*ThreadLocalUtil.set(user.getId());*/
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        //生成token
        String token = UUID.randomUUID().toString();


        LoginVO loginVO = LoginVO.builder()
                .user(userVO)
                .token(token)
                .build();



        long ttlMillis = 60L * 60 * 24 * 30;
        //创建UserLogin
        UserLogin userLogin = UserLogin
                .builder()
                .userId(user.getId())
                .token(token)
                .loginTime(LocalDateTime.now())
                .expireTime(LocalDateTime.now().plusSeconds(ttlMillis))
                .lastActiveTime(LocalDateTime.now())
                .status(1)
                .build();

        //保存到sqlserver的登录状态中
        userLoginMapper.insert(userLogin);

        return loginVO;
    }

    @Override
    public void add(UserCreateDTO userCreateDTO) {
        //判断用户名是否存在
        User user = userMapper.selectByUsername(userCreateDTO.getUsername());
        if (user != null) {
            throw new BaseException("用户名已存在");
        }
        user = new User();
        BeanUtils.copyProperties(userCreateDTO, user);
        user.setPassword(PasswordUtil.encode(userCreateDTO.getPassword()));
        user.setStatus(1);
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);

    }

    @Override
    public void update(UserUpdateDTO userUpdateDTO) {
        //更新
        User user = new User();
        BeanUtils.copyProperties(userUpdateDTO, user);
        userMapper.updateById(user);

    }

    @Override
    public void updateStatus(Long id, Integer status) {
        //不能封禁当前登录用户
        if (id.equals(ThreadLocalUtil.get())) {
            throw new BaseException("不能封禁当前登录用户");
        }
        //更新用户状态
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        userMapper.updateById(user);
    }

    @Override
    public PageResult<UserVO> page(UserPageQueryDTO userPageQueryDTO) {
        PageHelper.startPage(userPageQueryDTO.getPageNum(), userPageQueryDTO.getPageSize());
        Page<UserVO> page = userMapper.selectPage(userPageQueryDTO);
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    @Override
    public UserVO detail(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new BaseException("用户不存在");
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {
        //根据用户名查询密码
        Long id = ThreadLocalUtil.get();
        User user = userMapper.selectById(id);

        //判断密码是否匹配
        if (!PasswordUtil.matches(userPasswordDTO.getOldPassword(), user.getPassword())) {
            throw new BaseException("旧密码错误");
        }
        //判断新密码是否与确认密码一致
        if (!userPasswordDTO.getNewPassword().equals(userPasswordDTO.getConfirmPassword())) {
            throw new BaseException("新密码与确认密码不一致");
        }
        //更新密码

        userMapper.updatePwd(id, PasswordUtil.encode(userPasswordDTO.getNewPassword()));
    }

    @Override
    public void forceLogout(Long id) {
        //不能强制登出当前登录用户
        if (id.equals(ThreadLocalUtil.get())) {
            throw new BaseException("不能强制登出当前登录用户");
        }
        allLogout(id);
    }

    @Override
    public void logout() {
        //threadLocal获取id
        Long id = ThreadLocalUtil.get();
        allLogout(id);

    }

    @Override
    public void delete(Long id) {
        //不能删除当前登录用户
        if (id.equals(ThreadLocalUtil.get())) {
            throw new BaseException("不能删除当前登录用户");
        }

        //如果用户下有订单，不能删除用户
        if(orderMapper.selectByUserId(id)>0){
            throw new BaseException("用户下有订单，不能删除用户");
        }
        //设置登录状态为失效/已登出
        /*UserLogin newUserLogin = new UserLogin();
        newUserLogin.setId(employeeLogin.getId());
        newUserLogin.setStatus(0);
        userLoginMapper.update(newUserLogin);*/

        //删除登录状态
        userLoginMapper.deleteByUserId(id);


        //删除用户账号
        userMapper.deleteById(id);
    }

    @Override
    public void resetPassword(Long id) {

        //重置密码
        User user = new User();
        user.setId(id);
        user.setPassword(PasswordUtil.encode("123456"));
        userMapper.updateById(user);
    }


    private void allLogout(Long userId) {
        UserLogin ul = userLoginMapper.selectByUserId(userId);
        if (ul == null) {
            throw new BaseException("用户账号未登录");
        }
        UserLogin userLogin = UserLogin.builder()
                .id(ul.getId())
                .expireTime(LocalDateTime.now())
                .status(0)
                .build();
        //将token设置为失效
        userLoginMapper.updateLogin(userLogin);
    }
}
