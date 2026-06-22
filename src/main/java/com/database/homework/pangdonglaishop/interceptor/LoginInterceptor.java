package com.database.homework.pangdonglaishop.interceptor;


import com.database.homework.pangdonglaishop.entity.UserLogin;
import com.database.homework.pangdonglaishop.mapper.UserLoginMapper;

import com.database.homework.pangdonglaishop.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

/**
 * 登录拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserLoginMapper userLoginMapper;


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        //获取请求头中的token
        String token = request.getHeader("authorization");
        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }


        //校验token
        UserLogin userLogin = userLoginMapper.selectByToken(token);
        if (userLogin == null) {
            //token不存在
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //token存在
        //校验token是否过期
        //状态是否正常
        if (userLogin.getStatus() == 0) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        if (userLogin.getExpireTime().isBefore(LocalDateTime.now())) {
            //token过期
            //更新状态为0
            userLoginMapper.updateStatus(userLogin.getId(), 0);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //状态正常


        long ttlMillis = 60L * 60 * 24 * 30;
        //更新最后活跃时间
        UserLogin newUserLogin = UserLogin.builder()
                .id(userLogin.getId())
                .expireTime(LocalDateTime.now().plusSeconds(ttlMillis))
                .lastActiveTime(LocalDateTime.now())
                .build();
        userLoginMapper.update(newUserLogin);

        //保存到threadlocal
        ThreadLocalUtil.set(userLogin.getUserId());

        return true;
    }


    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }

}
