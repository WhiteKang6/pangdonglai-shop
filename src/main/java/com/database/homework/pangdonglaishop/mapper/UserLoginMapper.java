package com.database.homework.pangdonglaishop.mapper;

import com.database.homework.pangdonglaishop.entity.UserLogin;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserLoginMapper {

    @Select("select * from user_login where token = #{token}")
    UserLogin selectByToken(String token);

    @Update("update user_login set status = #{status} where id = #{id}")
    void updateStatus(Long id, int status);

    @Update("update user_login set expire_time = #{expireTime},last_active_time=#{lastActiveTime} where id = #{id}")
    void update(UserLogin newUserLogin);

    @Insert("insert into user_login(user_id, token, login_time, expire_time, last_active_time, status) " +
            "values (#{userId},#{token},#{loginTime},#{expireTime},#{lastActiveTime},#{status})")
    void insert(UserLogin userLogin);

    @Select("select * from user_login where user_id =#{userId} and status=1")
    UserLogin selectByUserId(Long userId);

    @Delete("delete from user_login where user_id=#{userId}")
    void deleteByUserId(Long userId);

    @Update("update user_login set expire_time = #{expireTime},status=#{status} where id = #{id} and status=1")
    void updateLogin(UserLogin userLogin);
}
